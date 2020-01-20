package com.example.library.dto.response;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "borrows")
@NoArgsConstructor
public class CustomerFullResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private List<BorrowedMinimalResponseDto> borrows;
}
