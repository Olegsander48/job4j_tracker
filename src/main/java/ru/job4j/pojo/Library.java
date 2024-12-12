package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book headFirst = new Book("Head first learn java", 709);
        Book cleanCode = new Book("Clean code", 1200);
        Book thinkingInJava = new Book("Thinking in java", 1700);
        Book effectiveJava = new Book("Effective java", 811);
        Book[] books = new Book[4];
        books[0] = headFirst;
        books[1] = cleanCode;
        books[2] = thinkingInJava;
        books[3] = effectiveJava;
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println(book.getName() + " : " + book.getPages());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println(book.getName() + " : " + book.getPages());
        }
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            if ("Clean code".equals(book.getName())) {
                System.out.println("Book with the name of clean code is: " + book.getName() + " - " + book.getPages());
            }
        }
    }
}
