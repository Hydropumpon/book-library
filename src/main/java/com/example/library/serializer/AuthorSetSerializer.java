package com.example.library.serializer;

import com.example.library.model.Author;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class AuthorSetSerializer extends StdSerializer<Set<Author>>
{

	public AuthorSetSerializer(Class<Set<Author>> t)
	{
		super(t);
	}

	public AuthorSetSerializer()
	{
		this(null);
	}


	@Override
	public void serialize(Set<Author> authors, JsonGenerator gen, SerializerProvider provider) throws IOException
	{
		gen.writeStartArray();
		for (Author author : authors)
		{
			gen.writeStartObject();
			gen.writeObjectField("id", author.getId());
			gen.writeObjectField("name", author.getName());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
}
