package com.example.library.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@JsonIgnoreProperties(value = "authors")
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

	@Builder.Default
	@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@BatchSize(size = 10)
	private Set<Book> books = new HashSet<>();

	public Author(String name)
	{
		this.name = name;
	}

	public void addBook(Book book)
	{
		books.add(book);
	}

	public void removeBook(Book book)
	{
		books.remove(book);
	}

	@Override
	public String toString()
	{
		return "Author{" + "id=" + id + ", name='" + name + books.stream().map(Book::getTitle)
																 .collect(Collectors.toList()) + '}';
	}
}
