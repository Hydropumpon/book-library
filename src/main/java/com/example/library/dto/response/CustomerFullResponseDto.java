package com.example.library.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CustomerFullResponseDto {
    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String login;

    private final String email;

    private final List<BorrowedMinimalResponseDto> borrows;
}
