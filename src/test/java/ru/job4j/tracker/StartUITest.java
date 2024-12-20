package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StartUITest {
    @Test
    void whenAddItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input input = new MockInput(
                new String[] {"0", "New item", "1"}
        );
        UserAction[] actions = {
                new CreateAction(),
                new ExitAction()
        };
        new StartUI().init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo("New item");
    }

    @Test
    void whenCreateItem() {
        Input input = new MockInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(),
                new ExitAction()
        };
        new StartUI().init(input, tracker, actions);
        assertThat(tracker.findAll()[0].getName()).isEqualTo("Item name");
    }

    @Test
    void whenAddTwoItems() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("First item"));
        Item item = tracker.add(new Item("Second item"));
        Input input = new MockInput(
                new String[] {"0", "First item", "0", "Second item", "1"}
        );
        UserAction[] actions = {
                new CreateAction(),
                new ExitAction()
        };
        new StartUI().init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo("Second item");
    }

    @Test
    void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input input = new MockInput(
                new String[] {"0", Integer.toString(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(),
                new ExitAction()
        };
        new StartUI().init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[] {"0", Integer.toString(item.getId()) , "1"}
        );
        UserAction[] actions = {
                new DeleteAction(),
                new ExitAction()
        };
        new StartUI().init(input, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }
}