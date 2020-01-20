package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Setter
public class CustomerMinimalResponseDto {
    private Long id;

    private String login;

    private String email;
}
