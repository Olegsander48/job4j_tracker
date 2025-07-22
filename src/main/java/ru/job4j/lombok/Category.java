package ru.job4j.lombok;

import lombok.*;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class Category {
    private final int id;
    @EqualsAndHashCode.Exclude
    @Setter
    private String name;
}
