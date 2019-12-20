package com.example.library.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authors")
public class BookDto
{
	private Long id;

	@NotNull
	@NotBlank
	private String title;

	private String description;

	private Set<AuthorDto> authors;
}
