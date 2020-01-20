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
import java.io.Serializable;


@Getter
@AllArgsConstructor
@Builder
public class AuthorRequestDto implements Serializable {
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    private final Long id;

    @NotNull(groups = {New.class, Update.class})
    @NotBlank(groups = {New.class, Update.class})
    private final String name;
}
