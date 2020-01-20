package com.example.library.controller.util;

import com.example.library.dto.request.AuthorRequestDto;
import com.example.library.dto.response.AuthorFullResponseDto;
import com.example.library.dto.response.AuthorMinimalResponseDto;
import com.example.library.model.Author;
import com.example.library.model.Book;

import java.util.ArrayList;
import java.util.HashSet;

public class AuthorControllerTestUtil {
    private static final String ADD_NAME = "Test";
    private static final String UPDATE_NAME = "Test2";
    public static final Long AUTHOR_ID = 1L;

    public static final AuthorRequestDto authorRequestDtoAdd;

    public static final AuthorRequestDto authorRequestDtoUpdate;

    public static final Author authorPreAdd;

    public static final Author authorDbAdd;

    public static final AuthorMinimalResponseDto authorMinimalResponseDto;

    public static final AuthorFullResponseDto authorFullResponseDto;

    public static final AuthorMinimalResponseDto authorMinimalResponseDtoUpdate;

    public static final AuthorMinimalResponseDto authorResponseDtoUpdate;

    public static final Author authorPreUpdate;

    public static final Author authorDbUpdate;

    //public static final AuthorFullResponseDto authorFullResponseDtoUpdate;

    static {
        authorRequestDtoAdd = new AuthorRequestDto();
        authorRequestDtoAdd.setName(ADD_NAME);

        authorMinimalResponseDto = new AuthorMinimalResponseDto();
        authorMinimalResponseDto.setName(authorRequestDtoAdd.getName());
        authorMinimalResponseDto.setId(AUTHOR_ID);

        authorFullResponseDto = new AuthorFullResponseDto();
        authorFullResponseDto.setName(authorRequestDtoAdd.getName());
        authorFullResponseDto.setId(AUTHOR_ID);
        authorFullResponseDto.setBooks(new ArrayList<>());

        authorRequestDtoUpdate = new AuthorRequestDto();
        authorRequestDtoUpdate.setName(UPDATE_NAME);
        authorRequestDtoUpdate.setId(AUTHOR_ID);

        authorPreAdd = new Author();
        authorPreAdd.setName(authorRequestDtoAdd.getName());

        authorDbAdd = new Author();
        authorDbAdd.setName(authorRequestDtoAdd.getName());
        authorDbAdd.setId(AUTHOR_ID);

        authorMinimalResponseDtoUpdate = new AuthorMinimalResponseDto();
        authorMinimalResponseDtoUpdate.setName(authorRequestDtoUpdate.getName());
        authorMinimalResponseDtoUpdate.setId(authorRequestDtoUpdate.getId());

        authorDbUpdate = new Author();
        authorDbUpdate.setName(authorRequestDtoUpdate.getName());
        authorDbUpdate.setId(authorRequestDtoUpdate.getId());

        authorPreUpdate = new Author();
        authorPreUpdate.setName(authorRequestDtoUpdate.getName());
        authorPreUpdate.setId(authorRequestDtoUpdate.getId());

        authorResponseDtoUpdate = new AuthorMinimalResponseDto();
        authorResponseDtoUpdate.setName(authorDbUpdate.getName());
        authorResponseDtoUpdate.setId(authorDbUpdate.getId());
    }
}
