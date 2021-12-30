package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date: 11.11.2021
 * Project: GafalagParser
 * Class: Deserializer
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@NoArgsConstructor
public class Deserializer {

    private List<PdfPage> pdfPages;
    private int beginPage;
    private int endPage;

    public Deserializer(@NonNull String jsonFile) {
        initPages(jsonFile);
    }

    public Deserializer(@NonNull String jsonFile, int beginPage, int endPage) {
        this.beginPage = beginPage;
        this.endPage = endPage;
        initPages(jsonFile);
    }

    private void initPages(String jsonFile) {
        try {
            pdfPages = new ObjectMapper().readerFor(new TypeReference<List<PdfPage>>() {}).readValue(new File(jsonFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Text> initTokens() {
        return pdfPages.stream()
                .skip(beginPage > 1 ? beginPage - 1 : 0)
                .limit((endPage > 2 && endPage > beginPage) ? endPage + 1 - beginPage : 1)
                .flatMap(pdfPage -> pdfPage.getTextBlocks().stream())
                .collect(Collectors.toList());
        // List<Text> tokens;
        // if (beginPage == 0 && endPage == 0) {
        //     tokens = pdfPages.stream().flatMap(pdfPage -> pdfPage.getTextBlocks().stream())
        //             .collect(Collectors.toList());
        // } else {
        //     tokens = pdfPages.stream().skip(beginPage > 1 ? beginPage - 1 : 0)
        //             .limit(endPage > 2 && endPage > beginPage ? endPage + 1 - beginPage : 1)
        //             .flatMap(pdfPage -> pdfPage.getTextBlocks().stream())
        //             .collect(Collectors.toList());
        // }
        // return tokens;
    }
}
