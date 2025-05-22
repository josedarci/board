package com.board.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Column {
    private Long id;
    private String name;
    private ColumnType type;

    @Builder.Default
    private List<Card> cards = new ArrayList<>();
}
