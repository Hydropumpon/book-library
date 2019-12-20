package com.example.library.converter;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.io.Serializable;
import java.util.IdentityHashMap;
import java.util.Map;

public class CycleAvoidingMappingContext
{
	private Map<Object, Object> knownInstances = new IdentityHashMap<>();

	@BeforeMapping
	public <T extends Serializable> T getMappedInstance(Object source, @TargetType Class<T> targetType)
	{
		return targetType.cast(knownInstances.get(source));
	}

	@BeforeMapping
	public void storeMappedInstance(Object source, @MappingTarget Object target)
	{
		knownInstances.put(source, target);
	}

}
