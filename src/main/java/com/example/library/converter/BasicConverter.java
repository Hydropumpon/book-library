package com.example.library.converter;

public interface BasicConverter<E,D>
{
	E fromDto(D dto);
	D toDto(E entity);
}
