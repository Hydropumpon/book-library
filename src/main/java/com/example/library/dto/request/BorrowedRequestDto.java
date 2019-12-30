package com.example.library.dto.request;

import com.example.library.validation.New;
import com.example.library.validation.Update;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class BorrowedRequestDto
{
	@NotNull(groups = {Update.class})
	@Null(groups = {New.class})
	private Long id;

	@NotNull(groups = {New.class})
	private Long customer;

	@NotNull(groups = {New.class})
	private Long book;

}
