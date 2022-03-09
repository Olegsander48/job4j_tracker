package ru.job4j.inheritance;

public class Builder extends Engineer {

    private String work;

    public Builder(String name, String surname, String education, String birthday, boolean research, String work) {
        super(name, surname, education, birthday, research);
        this.work = work;
    }

    public House build(Material material) {
        return null;
    }
}
