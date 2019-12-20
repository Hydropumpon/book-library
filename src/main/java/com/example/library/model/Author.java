package com.example.library.model;


import com.example.library.serializer.BookSetSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "author")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "books")
public class Author implements Serializable
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true)
	@Size(max = 255)
	@NotNull
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns =
	@JoinColumn(name = "book_id"))
	@JsonSerialize(using = BookSetSerializer.class)
	private Set<Book> books = new HashSet<Book>();

	@Override
	public String toString()
	{
		return "Author{" + "id=" + id + ", name='" + name + books.stream().map(Book::getTitle)
																 .collect(Collectors.toList()) + '}';
	}
}
