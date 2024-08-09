package com.sparta.springasignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class ScheduleRequestDto {
    private Long managerId;
    private String password;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
