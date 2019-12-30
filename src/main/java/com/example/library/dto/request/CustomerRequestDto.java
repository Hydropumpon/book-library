package com.example.library.dto.request;

import com.example.library.validation.New;
import com.example.library.validation.Update;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerRequestDto
{
	@Null(groups = {New.class})
	@NotNull(groups = {Update.class})
	private Long id;

	@NotNull(groups = {Update.class, New.class})
	@NotBlank(groups = {Update.class, New.class})
	private String login;

	@NotNull(groups = {Update.class, New.class})
	@NotBlank(groups = {Update.class, New.class})
	private String firstName;

	@NotNull(groups = {Update.class, New.class})
	@NotBlank(groups = {Update.class, New.class})
	private String lastName;

	@NotNull(groups = {Update.class, New.class})
	@NotBlank(groups = {Update.class, New.class})
	private String email;
}
