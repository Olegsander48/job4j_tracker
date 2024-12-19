package ru.job4j.polymorphism;

public class Bus implements Transport {
    @Override
    public void move() {
        System.out.println("Bus started move");
    }

    @Override
    public void passengers(int count) {
        System.out.println("Bus capacity is " + count + " passengers");
    }

    @Override
    public int refuel(int fuel) {
        return fuel;
    }
}
