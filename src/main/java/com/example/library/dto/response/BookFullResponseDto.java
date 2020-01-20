package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class BookFullResponseDto {
    private final Long id;

    private final String title;

    private final String description;

    private final Integer quantity;

    private final List<AuthorMinimalResponseDto> authors;
}
