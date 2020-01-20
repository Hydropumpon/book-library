package com.example.library.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authors")
public class BookFullResponseDto {
    private Long id;

    private String title;

    private String description;

    private Integer quantity;

    private List<AuthorMinimalResponseDto> authors;
}
