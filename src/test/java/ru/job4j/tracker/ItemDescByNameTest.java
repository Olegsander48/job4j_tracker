package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class ItemDescByNameTest {
    @Test
    void whenSort() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "Open"));
        items.add(new Item(2, "Close"));
        items.add(new Item(3, "Wait"));
        items.add(new Item(4, "Turn"));
        items.sort(new ItemDescByName());

        List<Item> expected = new ArrayList<>();
        expected.add(new Item(3, "Wait"));
        expected.add(new Item(4, "Turn"));
        expected.add(new Item(1, "Open"));
        expected.add(new Item(2, "Close"));

        assertThat(items).isEqualTo(expected);
    }

}