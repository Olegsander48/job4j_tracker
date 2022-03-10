package ru.job4j.pojo;

import ru.job4j.ru.job4j.pojo.Student;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Prigodich Aleksandr Nikolaevich");
        student.setGroup(10702219);
        student.setReceiptDate(new Date(2019 - 1900, 7, 01, 19, 00));

        System.out.println("Student " + student.getFio() + " from group " + student.getGroup() + " entered " + student.getReceiptDate());
    }
}
