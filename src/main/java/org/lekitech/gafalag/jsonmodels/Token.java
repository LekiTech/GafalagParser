package org.lekitech.gafalag.jsonmodels;

/**
 * Date: 28.10.2021
 * Project: GafalagParser
 * Class: Token
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class Token {

    private final Text text;

    public Token(Text text) {
        this.text = text;
    }

    public String getText() {
        return text.getRun().get(0).getTextBlock();
    }

    public Color getColor() {
        return Color.of(text);
    }

    public boolean isValid() {
        return (text.getY() > 1.012d); // valid page area
    }

    public boolean isStartNewArticle() {
        return text.getX() == 2.337 && getColor() == Color.BLUE; // {x = 2.337} - indent for new article
    }
}
