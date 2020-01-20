package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class BorrowedFullResponseDto {
    private final Long id;

    private final CustomerMinimalResponseDto customer;

    private final BookMinimalResponseDto book;

    private final String returnDate;

    private final String borrowDate;

    private final String returnTillDate;
}
