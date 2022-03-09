package ru.job4j.inheritance;

public class Dentist extends Doctor {

    private boolean careful;

    public Dentist(String name, String surname, String education, String birthday, boolean experience, boolean careful) {
        super(name, surname, education, birthday, experience);
        this.careful = careful;
    }

    public Diagnosis drill(Pacient pacient) {
        return null;
    }
}
