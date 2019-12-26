package com.example.library.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class BorrowedDto
{
	private Long id;

	@NotNull
	private CustomerDto customer;

	@NotNull
	private BookDto book;

	private String borrowDate;

	private String returnDate;

	private String returnTillDate;
}
