package org.lekitech.gafalag.dictionarymodels;

/**
 * Date: 07.10.2021
 * Project: GafalagParser
 * Class: Word
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class Word {

    private final String text;
    private final boolean isFirst;
    private final boolean isExample;
    private final boolean isMeaning;

    public Word(String text, boolean isFirst, boolean isExample, boolean isMeaning) {
        this.text = text;
        this.isFirst = isFirst;
        this.isExample = isExample;
        this.isMeaning = isMeaning;
    }

    public String getText() {
        return text;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isExample() {
        return isExample;
    }

    public boolean isMeaning() {
        return isMeaning;
    }

    @Override
    public String toString() {
        return String.format(
                "Word{text='%s', isFirst='%s', isExample='%s', isMeaning='%s'}",
                text, isFirst, isExample, isMeaning
        );
    }
}
