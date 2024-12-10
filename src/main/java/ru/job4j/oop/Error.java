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
        System.out.println("Содержимое ошибки: " + message);
    }

    public static void main(String[] args) {
        Error def = new Error();
        def.printInfo();
        Error success = new Error(true, 200, "Ok");
        success.printInfo();
        Error redirect = new Error(false, 300, "Multiple choices");
        redirect.printInfo();
    }
}
