package com.sparta.springasignment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class ManagerResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
