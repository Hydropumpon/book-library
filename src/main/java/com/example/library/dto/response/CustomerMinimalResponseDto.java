package com.example.library.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerMinimalResponseDto
{
	private Long id;

	private String login;

	private String email;
}
