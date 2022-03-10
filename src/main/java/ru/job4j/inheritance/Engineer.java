package ru.job4j.inheritance;

public class Engineer extends Profession {

    private boolean intelligence;

    public Engineer(String name, String surname, String education, String birthday, boolean research) {
        super(name, surname, education, birthday);
        this.intelligence = research;
    }

    public Product create(Material material) {
        return null;
    }
}
