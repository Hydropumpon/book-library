package com.example.library.converter;

import com.example.library.dto.BorrowedDto;
import com.example.library.model.Borrowed;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerConverter.class, BookConverter.class})
public interface BorrowedConverter
{
	@Mapping(target = "returnTillDate", expression = "java(java.time.LocalDate.now().plusDays(30))")
	@Mapping(target = "returnDate", ignore = true)
	@Mapping(target = "borrowDate", expression = "java(java.time.LocalDate.now())")
	@Mapping(target = "customer", source = "customerDto")
	@Mapping(target = "book", source = "bookDto")
	Borrowed fromDto(BorrowedDto dto, @Context CycleAvoidingMappingContext context);

	@Mapping(target = "customerDto", source = "customer")
	@Mapping(target = "bookDto", source = "book")
	BorrowedDto toDto(Borrowed entity, @Context CycleAvoidingMappingContext context);
}
