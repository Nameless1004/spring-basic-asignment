package com.sparta.springasignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long scheduleId;
    private Long managerId;
    private String password;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
