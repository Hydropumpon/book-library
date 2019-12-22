package com.example.library.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
	private CustomerDto customerDto;
	@NotNull
	private BookDto bookDto;
}
