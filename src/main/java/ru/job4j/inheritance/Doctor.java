package ru.job4j.inheritance;

public class Doctor extends Profession {

    private boolean experience;

    public Doctor(String name, String surname, String education, String birthday, boolean experience) {
        super(name, surname, education, birthday);
        this.experience = experience;
    }

    public Doctor() {
    }

    public Diagnosis heal(Pacient pacient) {
        return null;
    }
}
