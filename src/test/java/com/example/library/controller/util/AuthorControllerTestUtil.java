package com.example.library.controller.util;

import com.example.library.dto.request.AuthorRequestDto;
import com.example.library.dto.response.AuthorFullResponseDto;
import com.example.library.dto.response.AuthorMinimalResponseDto;
import com.example.library.model.Author;

import java.util.ArrayList;

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

    static {
        authorRequestDtoAdd = AuthorRequestDto.builder()
                                              .name(ADD_NAME)
                                              .build();

        authorMinimalResponseDto = AuthorMinimalResponseDto.builder()
                                                           .name(authorRequestDtoAdd.getName())
                                                           .id(AUTHOR_ID)
                                                           .build();

        authorFullResponseDto = AuthorFullResponseDto.builder()
                                                     .name(authorRequestDtoAdd.getName())
                                                     .id(AUTHOR_ID)
                                                     .books(new ArrayList<>())
                                                     .build();

        authorRequestDtoUpdate = AuthorRequestDto.builder()
                                                 .id(AUTHOR_ID)
                                                 .name(UPDATE_NAME)
                                                 .build();

        authorPreAdd = new Author();
        authorPreAdd.setName(authorRequestDtoAdd.getName());

        authorDbAdd = new Author();
        authorDbAdd.setName(authorRequestDtoAdd.getName());
        authorDbAdd.setId(AUTHOR_ID);

        authorMinimalResponseDtoUpdate = AuthorMinimalResponseDto.builder()
                                                                 .name(authorRequestDtoUpdate.getName())
                                                                 .id(authorRequestDtoUpdate.getId())
                                                                 .build();

        authorDbUpdate = new Author();
        authorDbUpdate.setName(authorRequestDtoUpdate.getName());
        authorDbUpdate.setId(authorRequestDtoUpdate.getId());

        authorPreUpdate = new Author();
        authorPreUpdate.setName(authorRequestDtoUpdate.getName());
        authorPreUpdate.setId(authorRequestDtoUpdate.getId());


        authorResponseDtoUpdate = AuthorMinimalResponseDto.builder()
                                                          .name(authorDbUpdate.getName())
                                                          .id(authorDbUpdate.getId())
                                                          .build();
    }
}
