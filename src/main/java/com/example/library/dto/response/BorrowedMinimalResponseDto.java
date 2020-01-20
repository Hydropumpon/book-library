package com.example.library.dto.response;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BorrowedMinimalResponseDto {
    private Long id;

    private String customer;

    private String book;

    private String returnDate;

}
