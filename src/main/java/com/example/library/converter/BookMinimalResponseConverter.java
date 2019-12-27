package com.example.library.converter;

import com.example.library.dto.response.BookMinimalResponseDto;
import com.example.library.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface BookMinimalResponseConverter
{
	BookMinimalResponseDto toDto(Book book);
}
