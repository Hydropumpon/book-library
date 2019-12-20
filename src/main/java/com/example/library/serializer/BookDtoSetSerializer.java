package com.example.library.serializer;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class BookDtoSetSerializer extends StdSerializer<Set<BookDto>>
{

	public BookDtoSetSerializer(Class<Set<BookDto>> t)
	{
		super(t);
	}

	public BookDtoSetSerializer()
	{
		this(null);
	}


	@Override
	public void serialize(Set<BookDto> books, JsonGenerator gen, SerializerProvider provider) throws IOException
	{
		gen.writeStartArray();
		for (BookDto book: books )
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
