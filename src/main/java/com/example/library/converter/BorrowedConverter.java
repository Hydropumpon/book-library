package com.example.library.converter;

import com.example.library.configuration.LibraryProperties;
import com.example.library.dto.BorrowedDto;
import com.example.library.model.Borrowed;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CustomerConverter.class, BookConverter.class})
public abstract class BorrowedConverter
{
	@Autowired
	LibraryProperties libraryProperties;

	@Mapping(target = "returnTillDate", expression = "java(java.time.LocalDate.now().plusDays(libraryProperties" +
			".getDays" + "()))")
	@Mapping(target = "returnDate", ignore = true)
	@Mapping(target = "borrowDate", expression = "java(java.time.LocalDate.now())")
	public abstract Borrowed fromDto(BorrowedDto dto, @Context CycleAvoidingMappingContext context);

	@Mapping(target = "borrowDate", dateFormat = "dd.MM.yyyy")
	@Mapping(target = "returnDate", dateFormat = "dd.MM.yyyy")
	@Mapping(target = "returnTillDate", dateFormat = "dd.MM.yyyy")
	public abstract BorrowedDto toDto(Borrowed entity, @Context CycleAvoidingMappingContext context);

}
