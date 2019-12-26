package com.example.library.converter;

import java.io.Serializable;

public interface Mapper<Entity extends Serializable, Dto extends Serializable>
{
	Dto toDto(Entity entity);

	Entity fromDto(Dto dto);
}
