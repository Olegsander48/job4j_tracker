package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftStr = left.split("\\.");
        String[] rightStr = right.split("\\.");
        int leftNum = Integer.parseInt(leftStr[0]);
        int rightNum = Integer.parseInt(rightStr[0]);
        return Integer.compare(leftNum, rightNum);
    }

    public static void main(String[] args) {
        String left = "avb. adfa.";
        String[] leftStr = left.split("\\.");
        /*for (String stroka: leftStr) {
            System.out.println(Integer.parseInt(stroka));
        }*/
        System.out.println(Arrays.toString(leftStr));
    }
}
