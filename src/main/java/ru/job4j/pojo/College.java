package ru.job4j.pojo;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Prigodich Aleksander Nikolaevich");
        student.setGroup("10702219BA");
        student.setEntrance(new Date(2010, Calendar.DECEMBER, 1));
        System.out.println(student.getFio() + " studying in a " + student.getGroup()
                            + " group since " + student.getEntrance());
    }
}
