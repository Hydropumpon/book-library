package com.example.library.controller.util;

import com.example.library.dto.AuthorDto;
import com.example.library.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorControllerTestUtil
{
	private static final String ADD_NAME = "Test";
	private static final String UPDATE_NAME = "Test2";
	public static final Long AUTHOR_ID = 1L;

	public static final AuthorDto authorRequestDtoAdd;

	public static final AuthorDto authorResponseDtoAdd;

	public static final AuthorDto authorRequestDtoUpdate;

	public static final AuthorDto authorResponseDtoUpdate;

	public static final Author authorDbAdd;

	public static final Author authorPreAdd;

	public static final Author authorDbUpdate;

	public static final Author authorPreUpdate;

	static
	{
		authorRequestDtoAdd = new AuthorDto();
		authorRequestDtoAdd.setName(ADD_NAME);

		authorResponseDtoAdd = new AuthorDto();
		authorResponseDtoAdd.setName(authorRequestDtoAdd.getName());
		authorResponseDtoAdd.setId(AUTHOR_ID);

		authorRequestDtoUpdate = new AuthorDto();
		authorRequestDtoUpdate.setName(UPDATE_NAME);
		authorRequestDtoUpdate.setId(AUTHOR_ID);

		authorResponseDtoUpdate = new AuthorDto();
		authorResponseDtoUpdate.setName(authorRequestDtoUpdate.getName());
		authorResponseDtoUpdate.setId(authorRequestDtoUpdate.getId());

		authorPreAdd = new Author();
		authorPreAdd.setName(authorRequestDtoAdd.getName());

		authorPreUpdate = new Author();
		authorPreUpdate.setName(authorRequestDtoUpdate.getName());
		authorPreUpdate.setId(authorRequestDtoUpdate.getId());

		authorDbAdd = new Author();
		authorDbAdd.setName(authorRequestDtoAdd.getName());
		authorDbAdd.setId(AUTHOR_ID);

		authorDbUpdate = new Author();
		authorDbUpdate.setName(authorRequestDtoUpdate.getName());
		authorDbUpdate.setId(authorRequestDtoUpdate.getId());
	}
}
