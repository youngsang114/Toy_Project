package com.example.simple_board.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Api<T> {
    private T body;
    private Pagination pagination;
}
