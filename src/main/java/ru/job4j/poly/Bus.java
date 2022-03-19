package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void ride() {
        System.out.println("Bus ride");
    }

    @Override
    public void passengers(int passengersCount) {
        System.out.println("In bus " + passengersCount + " passangers");
    }

    @Override
    public double refuel(double fuel) {
        double fuelPrice = 2.16;
        return fuel * fuelPrice;
    }
}
