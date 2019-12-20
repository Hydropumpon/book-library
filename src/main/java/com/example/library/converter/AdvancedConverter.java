package com.example.library.converter;

import org.mapstruct.Context;

public interface AdvancedConverter<E,D>
{
	E fromDto(D dto, @Context CycleAvoidingMappingContext context);
	D toDto(E entity, @Context CycleAvoidingMappingContext context);
}
