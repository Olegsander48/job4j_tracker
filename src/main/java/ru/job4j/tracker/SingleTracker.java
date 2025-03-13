package ru.job4j.tracker;

import java.util.List;

public class SingleTracker {
    private static Store store = new MemTracker();
    private static SingleTracker singleTracker = null;

    private SingleTracker() {
    }

    public static SingleTracker getInstance() {
        if (singleTracker == null) {
            singleTracker = new SingleTracker();
        }
        return singleTracker;
    }

    public Item add(Item item) {
        return store.add(item);
    }

    public List<Item> findAll() {
        return store.findAll();
    }

    public Item findById(int id) {
        return store.findById(id);
    }

    public List<Item> findByName(String key) {
        return store.findByName(key);
    }

    public boolean replace(int id, Item item) {
        return store.replace(id, item);
    }

    public void delete(int id) {
        store.delete(id);
    }
}
