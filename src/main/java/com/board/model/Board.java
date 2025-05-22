package com.board.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private Long id;
    private String name;

    @Builder.Default
    private List<Column> columns = new ArrayList<>();
}
