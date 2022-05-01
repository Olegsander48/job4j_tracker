package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] mas1 = o1.split("/");
        String[] mas2 = o2.split("/");
        if (mas1[0].equals(mas2[0])) {
            return o1.compareTo(o2);
        } else {
            return mas2[0].compareTo(mas1[0]);
        }
    }
}
