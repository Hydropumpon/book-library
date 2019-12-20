package com.example.library.converter;

import com.example.library.dto.AuthorDto;
import com.example.library.model.Author;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = BookConverter.class)
public interface AuthorConverter extends AdvancedConverter<Author, AuthorDto>
{
}
