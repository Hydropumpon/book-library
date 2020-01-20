package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class AuthorFullResponseDto {
    private final Long id;

    private final String name;

    private final List<BookMinimalResponseDto> books;
}
