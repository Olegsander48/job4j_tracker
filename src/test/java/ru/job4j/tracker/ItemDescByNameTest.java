package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemDescByNameTest {
    @Test
    public void sortItemsByAsc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "Миша"));
        items.add(new Item(2, "Владимир"));
        items.add(new Item(3, "Илья"));

        Collections.sort(items, new ItemDescByName());

        List<Item> expected = new ArrayList<>();
        expected.add(new Item(1, "Миша"));
        expected.add(new Item(3, "Илья"));
        expected.add(new Item(2, "Владимир"));
        assertThat(items, is(expected));
    }
}