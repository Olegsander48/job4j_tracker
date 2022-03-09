package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активна ли ошибка: " + active);
        System.out.println("Статус ошибки: " + status);
        System.out.println("Сообщение ошибки: " + message);
    }

    public static void main(String[] args) {
        Error error404 = new Error();
        Error error400 = new Error(true, 400, "Bad Request");
        Error error500 = new Error(true, 500, "Internal Server Error");
        error404.printInfo();
        error400.printInfo();
        error500.printInfo();
    }
}
