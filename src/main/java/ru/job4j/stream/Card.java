package ru.job4j.stream;

import java.util.stream.Stream;

enum Value {
    V_6, V_7, V_8
}

enum Suit {
    Diamonds, Hearts, Spades, Clubs
}

public class Card {
    private Suit suits;
    private Value values;

    public Card(Suit suits, Value values) {
        this.suits = suits;
        this.values = values;
    }

    public static void main(String[] args) {
        Stream.of(Suit.values())
                .flatMap(suite -> Stream.of(Value.values())
                        .map(value -> suite + " " + value))
                .forEach(System.out::println);
    }
}

