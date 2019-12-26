package com.example.library.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
@EqualsAndHashCode(exclude = {"authors", "borrowedSet"})
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

	@NotNull
	@Min(0)
	private Integer quantity;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns =
	@JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();

	@OneToMany(targetEntity = Borrowed.class, mappedBy = "book", orphanRemoval = true)
	private Set<Borrowed> borrowedSet = new HashSet<>();

	public void removeAuthor(Author author)
	{
		authors.remove(author);
	}

	@Override
	public String toString()
	{
		return "Book{" + "id=" + id + ", title='" + title + ", description=" + description + ", authors=" + authors
				.stream().map(Author::getName).collect(Collectors.toList()) + '}';
	}
}
