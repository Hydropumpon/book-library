package com.example.library.converter;

import com.example.library.dto.response.BookFullResponseDto;
import com.example.library.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses =
		{AuthorMinimalResponseConverter.class})
public interface BookFullResponseConverter
{
	BookFullResponseDto toDto(Book book);
}
