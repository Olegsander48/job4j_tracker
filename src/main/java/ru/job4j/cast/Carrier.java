package ru.job4j.cast;

public class Carrier {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle bus = new Bus();
        Vehicle train = new Train();

        Vehicle[] vehicles = {plane, bus, train};
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }
}
