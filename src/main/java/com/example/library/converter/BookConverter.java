package com.example.library.converter;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = {AuthorConverter.class})
public interface BookConverter
{
	@Mapping(target = "borrowedSet", ignore = true)
	Book fromDto(BookDto dto, @Context CycleAvoidingMappingContext context);

	BookDto toDto(Book entity, @Context  CycleAvoidingMappingContext context);
}
