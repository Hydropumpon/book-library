package com.example.library.converter;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = AuthorConverter.class)
public interface BookConverter extends AdvancedConverter<Book, BookDto>
{
}
