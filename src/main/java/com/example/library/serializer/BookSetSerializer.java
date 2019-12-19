package com.example.library.serializer;

import com.example.library.model.Book;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class BookSetSerializer extends StdSerializer<Set<Book>>
{

	public BookSetSerializer(Class<Set<Book>> t)
	{
		super(t);
	}

	public BookSetSerializer()
	{
		this(null);
	}


	@Override
	public void serialize(Set<Book> books, JsonGenerator gen, SerializerProvider provider) throws IOException
	{
		gen.writeStartArray();
		for (Book book: books )
		{
			gen.writeStartObject();
			gen.writeObjectField("id", book.getId());
			gen.writeObjectField("title", book.getTitle());
			gen.writeObjectField("description", book.getDescription());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
}
