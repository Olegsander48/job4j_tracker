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

class FindByIdActionTest {
    @Test
    void whenItemWasFoundSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("New item"));
        FindByIdAction findByIdAction = new FindByIdAction(output);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявки по id ===" + ln
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
        FindByIdAction findByIdAction = new FindByIdAction(output);

        Input input = mock(Input.class);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявки по id ===" + ln
                        + "Заявка с введенным id: 0 не найдена." + ln);
    }

    @Test
    void whenItemWasNotFoundByIdSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        FindByIdAction findByIdAction = new FindByIdAction(output);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(8);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявки по id ===" + ln
                        + "Заявка с введенным id: 8 не найдена." + ln);
    }
}