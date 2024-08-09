package com.sparta.springasignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {
    private Long scheduleId;
    private Long managerId;
    private String contents;
}
