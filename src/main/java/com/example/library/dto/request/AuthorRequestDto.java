package com.example.library.dto.request;

import com.example.library.views.transfer.New;
import com.example.library.views.transfer.Update;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;


@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
public class AuthorRequestDto implements Serializable
{
	@Null(groups = {New.class})
	@NotNull(groups = {Update.class})
	private Long id;

	@NotNull(groups = {New.class, Update.class})
	@NotBlank(groups = {New.class, Update.class})
	private String name;
}
