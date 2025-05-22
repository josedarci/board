package com.board.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    private Long id;
    private String title;
    private String description;

    @Builder.Default
    private LocalDate creationDate = LocalDate.now();

    @Builder.Default
    private boolean blocked = false;

    private String blockReason;
}
