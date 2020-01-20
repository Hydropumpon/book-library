package com.example.library.dto.request;

import com.example.library.validation.New;
import com.example.library.validation.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;


@Getter
@AllArgsConstructor
@Builder
public class BookRequestDto {
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    private final Long id;

    @NotNull(groups = {Update.class, New.class})
    @NotBlank(groups = {Update.class, New.class})
    private final String title;

    private final String description;

    @NotNull(groups = {Update.class, New.class})
    @Min(value = 1, groups = {Update.class, New.class})
    private final Integer quantity;

    private final List<Long> authors;
}
