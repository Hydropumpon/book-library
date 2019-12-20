package com.example.library.serializer;

import com.example.library.dto.AuthorDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class AuthorDtoSetSerializer extends StdSerializer<Set<AuthorDto>>
{

	public AuthorDtoSetSerializer(Class<Set<AuthorDto>> t)
	{
		super(t);
	}

	public AuthorDtoSetSerializer()
	{
		this(null);
	}

	@Override
	public void serialize(Set<AuthorDto> authors, JsonGenerator gen, SerializerProvider provider) throws IOException
	{
		gen.writeStartArray();
		for (AuthorDto author : authors)
		{
			gen.writeStartObject();
			gen.writeObjectField("id", author.getId());
			gen.writeObjectField("name", author.getName());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
}
