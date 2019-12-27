package com.example.library.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BorrowedFullResponseDto
{
	private Long id;

	private CustomerMinimalResponseDto customer;

	private BookMinimalResponseDto book;

	private String returnDate;

	private String borrowDate;

	private String returnTillDate;
}
