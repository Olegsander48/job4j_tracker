package ru.job4j.oop;

public class Jukebox {
    public void music(int position) {
        if (position == 1) {
            System.out.println("Пусть бегут неуклюже\n"
                    + "Пешеходы по лужам,\n"
                    + "А вода по асфальту рекой.\n"
                    + "И неясно прохожим\n"
                    + "В этот день непогожий,\n"
                    + "Почему я веселый такой?");
        } else if (position == 2) {
            System.out.println("Спокойной ночи, господа, спокойной ночи,\n"
                    + "Ваш день прошел, а ночь колдует и пророчит.\n"
                    + "Ночные сказки, отголоски дней минувших\n"
                    + "Спокойной ночи, добрых снов для всех уснувших.");
        } else {
            System.out.println("Песня не найдена");
        }
    }

    public static void main(String[] args) {
        Jukebox melody = new Jukebox();
        melody.music(1);
        melody.music(2);
        melody.music(0);
    }
}
