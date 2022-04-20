package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int length, compare = Integer.compare(left.length(), right.length());
        if (compare > 0) {
            length = right.length();
        } else {
            length = left.length();
        }
        for (int i = 0; i < length; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return Character.compare(left.charAt(i), right.charAt(i));
            }
        }
        return compare;
    }
}
