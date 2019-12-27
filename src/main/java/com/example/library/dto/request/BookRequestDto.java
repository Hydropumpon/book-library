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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BookRequestDto
{
	@Null(groups = {New.class})
	@NotNull(groups = {Update.class})
	private Long id;

	@NotNull(groups = {Update.class, New.class})
	@NotBlank(groups = {Update.class, New.class})
	private String title;

	private String description;

	@NotNull(groups = {Update.class, New.class})
	private Integer quantity;

	private List<Long> authors;
}
