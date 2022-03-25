package ru.job4j.ex;

public class ElementNotFoundException extends Exception {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                rsl = i;
            } else {
                throw new ElementNotFoundException();
            }
            /*Search element not found in string array*/
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] value = {"a", "bcd", "gef"};
        try {
            ElementNotFoundException.indexOf(value, "f");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
