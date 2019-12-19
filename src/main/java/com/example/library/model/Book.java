package com.example.library.model;


import com.example.library.serializer.AuthorSetSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "authors")
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 255)
	@NotNull
	@Column(name = "title", unique = true)
	private String title;

	@Size(max = 4096)
	@Column(name = "description")
	private String description;

	@JsonSerialize(using = AuthorSetSerializer.class)
	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	private Set<Author> authors = new HashSet<Author>();

	@Override
	public String toString()
	{
		return "Book{" + "id=" + id + ", title='" + title + ", description=" + description + ", authors=" + authors
				.stream().map(Author::getName).collect(Collectors.toList()) + '}';
	}
}
