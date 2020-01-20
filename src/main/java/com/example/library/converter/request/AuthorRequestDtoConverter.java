package com.example.library.converter.request;

import com.example.library.dto.request.AuthorRequestDto;
import com.example.library.model.Author;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorRequestDtoConverter {
    @Mapping(target = "books", ignore = true)
    Author fromDto(AuthorRequestDto authorRequestDto);
}
