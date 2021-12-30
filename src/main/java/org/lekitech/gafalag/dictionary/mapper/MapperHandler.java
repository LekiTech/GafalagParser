package org.lekitech.gafalag.dictionary.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.lekitech.gafalag.sourcemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Date: 11.11.2021
 * Project: GafalagParser
 * Class: MapperHandler
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
public class MapperHandler {

    private final List<PdfPage> pdfPages;
    private Predicate<Text> startArticle = text -> text.getX() < 2.65 && text.colorIs(Color.BLUE);
    private Predicate<Text> validTextPositionOfPage = text -> text.getY() > 1.012d;
    private int startPage = 0, lastPage = Integer.MAX_VALUE;

    public MapperHandler(String jsonFile) throws IOException {
        pdfPages = new ObjectMapper()
                .readerFor(new TypeReference<List<PdfPage>>() {})
                .readValue(new File(jsonFile));
    }

    public MapperHandler setPagesInterval(int startPage, int lastPage) {
        this.startPage = (startPage > 1) ? startPage - 1 : 0;
        this.lastPage = (lastPage > 2 && lastPage > startPage) ? lastPage + 1 - startPage : 1;
        return this;
    }

    public List<Text> pagesToTextTokens() {
        return pdfPages.stream().skip(startPage).limit(lastPage)
                .flatMap(page -> page.getTextBlocks().stream())
                .filter(validTextPositionOfPage)
                .collect(Collectors.toList());
    }
}
