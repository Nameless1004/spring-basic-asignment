package com.sparta.springasignment.dto.manager.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ManagerResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
