package com.example.library.converter;

import com.example.library.dto.response.AuthorMinimalResponseDto;
import com.example.library.model.Author;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface AuthorMinimalResponseConverter
{
	AuthorMinimalResponseDto toDto(Author author);
}
