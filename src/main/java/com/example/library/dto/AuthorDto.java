package com.example.library.dto;

import com.example.library.serializer.BookDtoSetSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "books")
public class AuthorDto implements Serializable
{
	private Long id;
	@NotNull
	private String name;
	@JsonSerialize(using = BookDtoSetSerializer.class)
	private Set<BookDto> books;
}
