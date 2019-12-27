package com.example.library.dto;

import com.example.library.serializer.AuthorDtoSetSerializer;
import com.example.library.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"authors"})
public class BookDto
{
	private Long id;

	@NotNull
	@NotBlank
	private String title;

	@NotNull
	@Min(1)
	private Integer quantity;

	private String description;

	private Set<AuthorDto> authors = new HashSet<>();

}
