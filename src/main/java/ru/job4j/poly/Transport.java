package ru.job4j.poly;

public interface Transport {
    void ride();

    void passengers(int passengersCount);

    double refuel(double fuel);
}
