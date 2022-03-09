package ru.job4j.oop;

public class Jukebox {
    public void music(int position) {
        if (position == 1) {
            System.out.println("Пусть бегут неуклюже" + System.lineSeparator()
                    + "Пешеходы по лужам," + System.lineSeparator()
                    + "А вода по асфальту рекой." + System.lineSeparator()
                    + "И неясно прохожим" + System.lineSeparator()
                    + "В этот день непогожий," + System.lineSeparator()
                    + "Почему я веселый такой?" + System.lineSeparator());
        } else if (position == 2) {
            System.out.println("Спокойной ночи, господа, спокойной ночи," + System.lineSeparator()
                    + "Ваш день прошел, а ночь колдует и пророчит." + System.lineSeparator()
                    + "Ночные сказки, отголоски дней минувших" + System.lineSeparator()
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
