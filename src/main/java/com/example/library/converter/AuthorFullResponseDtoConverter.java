package com.example.library.converter;

import com.example.library.dto.response.AuthorFullResponseDto;
import com.example.library.model.Author;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = {BookMinimalResponseConverter.class})
public interface AuthorFullResponseDtoConverter
{
	AuthorFullResponseDto toDto(Author author);
}
