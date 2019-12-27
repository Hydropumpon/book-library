package com.example.library.dto;

import com.example.library.serializer.BookDtoSetSerializer;
import com.example.library.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"books"})
public class AuthorDto implements Serializable
{
	@JsonView(Views.IdName.class)
	private Long id;

	@NotNull
	@JsonView(Views.IdName.class)
	private String name;

	@JsonView(Views.FullData.class)
	@JsonSerialize(using = BookDtoSetSerializer.class)
	private Set<BookDto> books = new HashSet<>();
}
