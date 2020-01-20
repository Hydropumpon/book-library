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
public class BorrowedMinimalResponseDto {
    private final Long id;

    private final String customer;

    private final String book;

    private final String returnDate;

}
