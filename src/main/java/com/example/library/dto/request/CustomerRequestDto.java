package com.example.library.dto.request;

import com.example.library.validation.New;
import com.example.library.validation.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@AllArgsConstructor
@Builder
public class CustomerRequestDto {
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    private final Long id;

    @NotNull(groups = {Update.class, New.class})
    @NotBlank(groups = {Update.class, New.class})
    private final String login;

    @NotNull(groups = {Update.class, New.class})
    @NotBlank(groups = {Update.class, New.class})
    private final String firstName;

    @NotNull(groups = {Update.class, New.class})
    @NotBlank(groups = {Update.class, New.class})
    private final String lastName;

    @NotNull(groups = {Update.class, New.class})
    @NotBlank(groups = {Update.class, New.class})
    private final String email;
}
