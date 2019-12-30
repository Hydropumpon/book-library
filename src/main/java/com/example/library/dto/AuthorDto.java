package com.example.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = "books")
public class AuthorDto implements Serializable
{
	private Long id;

	@NotNull
	private String name;

	private Set<BookDto> books = new HashSet<>();
}
