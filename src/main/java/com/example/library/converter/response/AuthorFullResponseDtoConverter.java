package com.example.library.converter.response;

import com.example.library.dto.response.AuthorFullResponseDto;
import com.example.library.model.Author;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        uses = {BookMinimalResponseDtoConverter.class})
public interface AuthorFullResponseDtoConverter {
    AuthorFullResponseDto toDto(Author author);
}
