package com.example.library.converter;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = {AuthorConverter.class})
public interface BookConverter
{
	Book fromDto(BookDto dto, @Context CycleAvoidingMappingContext context);

	BookDto toDto(Book entity, @Context  CycleAvoidingMappingContext context);
}
