package ru.job4j.tracker.action;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.time.format.DateTimeFormatter;

class FindByNameActionTest {
    @Test
    void whenItemWasFoundSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("New item"));
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявок по имени ===" + ln
                        + "Item{" + "id=" + item.getId()
                        + ", name='" + item.getName()
                        + '\'' + ", created="
                        + item.getCreated().format(DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss"))
                        + '}' + ln);
    }

    @Test
    void whenTwoItemsWasFoundSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("New item"));
        tracker.add(item);
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявок по имени ===" + ln
                        + "Item{" + "id=" + item.getId()
                        + ", name='" + item.getName()
                        + '\'' + ", created="
                        + item.getCreated().format(DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss"))
                        + '}' + ln
                        + "Item{" + "id=" + item.getId()
                        + ", name='" + item.getName()
                        + '\'' + ", created="
                        + item.getCreated().format(DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss"))
                        + '}' + ln);
    }

    @Test
    void whenItemWasNotFoundSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявок по имени ===" + ln
                        + "Заявки с именем: null не найдены." + ln);
    }

    @Test
    void whenItemWasNotFoundByNameSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("item");

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявок по имени ===" + ln
                        + "Заявки с именем: item не найдены." + ln);
    }
}