package ru.job4j.inheritance;

public class Programmer extends Engineer {

    private boolean drinkCoffee;

    public Programmer(String name, String surname, String education, String birthday, boolean research, boolean drinkCoffee) {
        super(name, surname, education, birthday, research);
        this.drinkCoffee = drinkCoffee;
    }

    public Application write(Code code) {
        return null;
    }
}
