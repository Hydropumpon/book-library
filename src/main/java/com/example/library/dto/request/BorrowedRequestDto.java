package com.example.library.dto.request;

import com.example.library.validation.New;
import com.example.library.validation.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Builder
@AllArgsConstructor
public class BorrowedRequestDto {
    @NotNull(groups = {Update.class})
    @Null(groups = {New.class})
    private final Long id;

    @NotNull(groups = {New.class})
    private final Long customer;

    @NotNull(groups = {New.class})
    private final Long book;

}
