package com.vay.messenger.web.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PostDto {

    @NotNull(message = "Id must be not null")
    private Long id;

    @NotNull(message = "Title must be not null")
    @Length(max = 255, message = "Title length must be smaller than 255 symbols")
    private String title;

    @NotNull(message = "Description must be not null")
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
}
