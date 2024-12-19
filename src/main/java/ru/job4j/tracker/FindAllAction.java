package ru.job4j.tracker;

public class FindAllAction implements UserAction {
    @Override
    public String name() {
        return "Показать все заявки";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            System.out.println("=== Вывод всех заявок ===");
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Хранилище еще не содержит заявок");
        }
        return true;
    }
}
