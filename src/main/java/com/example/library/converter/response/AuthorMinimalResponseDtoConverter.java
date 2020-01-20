package com.example.library.converter.response;

import com.example.library.dto.response.AuthorMinimalResponseDto;
import com.example.library.model.Author;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface AuthorMinimalResponseDtoConverter {
    AuthorMinimalResponseDto toDto(Author author);
}
