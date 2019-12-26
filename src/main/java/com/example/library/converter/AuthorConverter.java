package com.example.library.converter;

import com.example.library.dto.AuthorDto;
import com.example.library.model.Author;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = {BookConverter.class})
public interface AuthorConverter
{
	Author fromDto(AuthorDto dto, @Context CycleAvoidingMappingContext context);

	AuthorDto toDto(Author entity, @Context CycleAvoidingMappingContext context);
}
