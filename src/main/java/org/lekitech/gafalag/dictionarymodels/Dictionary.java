package org.lekitech.gafalag.dictionarymodels;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Date: 07.10.2021
 * Project: GafalagParser
 * Class: Dictionary
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class Dictionary {

    private Node first;
    private Node last;
    private final Map<String, String> dictionary = new LinkedHashMap<>();

    private static class Node {

        private final Word item;
        private Node next;

        Node(Word element, Node next) {
            this.item = element;
            this.next = next;
        }
    }

    public void add(Word value) {
        final Node l = last;
        final Node newNode = new Node(value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    public Map<String, String> getDictionary() {
        final StringBuilder key = new StringBuilder();
        final StringBuilder value = new StringBuilder();
        for (Node node = first; node != last; node = node.next) {
            final String text = node.item.getText().trim();
            if (node.item.isFirst()) {
                key.append(text).append(" ");
            } else {
                value.append(text).append(" ");
                if (node.next.item.isFirst()) {
                    dictionary.put(replace(key), replace(value));
                    key.setLength(0);
                    value.setLength(0);
                }
            }
        }
        dictionary.put(replace(key), replace(value));
        return dictionary;
    }

    private String replace(StringBuilder sb) {
        return sb.toString().trim()
                .replace(" - ", "-")
                .replace("( ", "(")
                .replace(" )", ")")
                .replace(" ;", ";")
                .replace(" .", ".")
                .replace(" ,", ",")
                .replace(" ,", ",")
                .replace(" ?", "?")
                .replace(" !", "!")
                .replace("1.", "1)")
                .replace("2.", "2)")
                .replace("3.", "3)")
                .replace("4.", "4)")
                .replace(" I ", "I");
    }
}
