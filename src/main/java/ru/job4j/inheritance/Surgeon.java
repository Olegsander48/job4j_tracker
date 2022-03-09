package ru.job4j.inheritance;

public class Surgeon extends Doctor {

    private boolean goodVision;

    public Surgeon(String name, String surname, String education, String birthday, boolean experience, boolean goodVision) {
        super(name, surname, education, birthday, experience);
        this.goodVision = goodVision;
    }

    public Diagnosis cut(Pacient pacient) {
        return null;
    }
}
