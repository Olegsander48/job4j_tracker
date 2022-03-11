package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book cleanCode = new Book("Clean code", 1000);
        Book java = new Book("Java for beginners", 1500);
        Book prostokvashino = new Book("Prostokvashino", 200);
        Book algorithms = new Book("Groming algorithms", 288);

        Book[] books = new Book[4];
        books[0] = cleanCode;
        books[1] = java;
        books[2] = prostokvashino;
        books[3] = algorithms;
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPagesCount());
        }

        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        System.out.println("Array after swapping 1 and 4 elements:");
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPagesCount());
        }

        System.out.println("Books with name: \"Clean code\":");
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            if ("Clean code".equals(bk.getName())) {
                System.out.println(bk.getName() + " - " + bk.getPagesCount());
            }
        }
    }
}
