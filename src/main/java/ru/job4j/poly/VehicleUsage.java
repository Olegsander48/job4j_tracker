package ru.job4j.poly;

public class VehicleUsage {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle autobus = new Autobus();

        Vehicle[] vehicles = new Vehicle[]{plane, train, autobus};
        for (Vehicle v: vehicles) {
            v.move();
        }
    }
}
