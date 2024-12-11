package ru.job4j.checkstyle;

public class Broken {
    private static final String NEW_VALUE = "";
    private final int sizeOfEmpty = 10;
    private String surname;
    private String name;

    public Broken() {
    }

    public void echo() {
    }

    public void inform(Object obj) {
        if (obj != null) {
            System.out.println(obj);
        }

    }

    public void method(int a, int b, int c, int d) {
    }
}