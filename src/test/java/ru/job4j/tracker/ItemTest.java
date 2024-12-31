package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemTest {
    @Test
    void whenSortAsc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(3, "Wait"));
        items.add(new Item(1, "Open"));
        items.add(new Item(4, "Turn"));
        items.add(new Item(2, "Close"));
        items.sort(new ItemAscByName());

        List<Item> expected = List.of(
                new Item(2, "Close"),
                new Item(1, "Open"),
                new Item(4, "Turn"),
                new Item(3, "Wait"));

        assertThat(items).isEqualTo(expected);
    }

    @Test
    void whenSortDesc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "Open"));
        items.add(new Item(2, "Close"));
        items.add(new Item(3, "Wait"));
        items.add(new Item(4, "Turn"));
        items.sort(new ItemDescByName());

        List<Item> expected = List.of(
                new Item(3, "Wait"),
                new Item(4, "Turn"),
                new Item(1, "Open"),
                new Item(2, "Close"));

        assertThat(items).isEqualTo(expected);
    }

}