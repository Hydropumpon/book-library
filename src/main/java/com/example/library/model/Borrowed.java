package com.example.library.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrowed
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne()
	@JoinColumn(name = "book_id")
	private Book book;

	private LocalDate borrowDate = LocalDate.now();

	private LocalDate returnDate;

	private LocalDate returnTillDate = LocalDate.now().plusDays(30);

}
