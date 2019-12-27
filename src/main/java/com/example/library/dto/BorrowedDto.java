package com.example.library.dto;

import com.example.library.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
	@JsonView(Views.IdName.class)
	private Long id;

	@JsonView(Views.FullData.class)
	@NotNull
	private CustomerDto customer;

	@JsonView(Views.FullData.class)
	@NotNull
	private BookDto book;

	@JsonView(Views.IdName.class)
	private String borrowDate;

	@JsonView(Views.IdName.class)
	private String returnDate;

	@JsonView(Views.IdName.class)
	private String returnTillDate;
}
