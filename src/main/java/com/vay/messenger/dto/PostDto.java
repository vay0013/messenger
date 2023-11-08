package com.vay.messenger.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private String Title;
    private String description;
    private LocalDateTime createdAt;
}
