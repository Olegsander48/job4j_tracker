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

class DeleteActionTest {
    @Test
    void whenItemWasDeletedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("New item"));
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Удаление заявки ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }

    @Test
    void whenItemWasNotDeletedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Удаление заявки ===" + ln
                        + "Ошибка удаления заявки." + ln
        );
    }
}