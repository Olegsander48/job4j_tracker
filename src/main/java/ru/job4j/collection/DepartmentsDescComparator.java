package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = right.compareTo(left);
        if (right.split("/")[0].equals(left.split("/")[0])) {
            result = left.compareTo(right);
        }
        return result;
    }
}
