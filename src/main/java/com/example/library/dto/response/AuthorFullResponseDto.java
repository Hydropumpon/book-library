package com.example.library.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "books")
public class AuthorFullResponseDto {
    private Long id;

    private String name;

    private List<BookMinimalResponseDto> books;
}
