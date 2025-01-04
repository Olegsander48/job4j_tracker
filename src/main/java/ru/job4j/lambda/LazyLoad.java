package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class LazyLoad {
    public static void main(String[] args) {
        String[] names = {
                "Ivan",
        };
        Comparator<String> lengthComparator = (left, right) -> {
            System.out.println("execute comparator");
            return Integer.compare(left.length(), right.length());
        };
        Arrays.sort(names, lengthComparator);

        names = new String[]{
                "Ivan",
                "Petr"
        };
        Arrays.sort(names, lengthComparator);
    }
}
