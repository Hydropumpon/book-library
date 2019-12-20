package com.example.library.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

//TODO @Entity
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

	private Customer customer;

	private Set<Book> books;

	private LocalDate borrowDate;

	private LocalDate returnDate;

}
