package com.example.library.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Set;

//TODO mark as @Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Borrowed
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private User user;

	private Set<Book> books;

	private LocalDate borrowDate;

	private LocalDate returnDate;

}
