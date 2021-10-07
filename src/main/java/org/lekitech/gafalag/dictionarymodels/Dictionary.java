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

        private Word item;
        private Node next;
        private Node prev;

        Node(Node prev, Word element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;

        }
    }

    public void add(Word value) {
        final Node l = last;
        final Node newNode = new Node(l, value, null);
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
                if (text.matches("\\p{Punct}")) {
                    key.append(text);
                } else {
                    key.append(" ").append(text);
                }
            } else {
                if (text.matches("\\p{Punct}")) {
                    value.append(text);
                } else {
                    value.append(" ").append(text);
                }
                if (node.next.item.isFirst()) {
                    dictionary.put(key.toString().trim(), value.toString().trim());
                    key.setLength(0);
                    value.setLength(0);
                }
            }
        }
        return dictionary;
    }
}

