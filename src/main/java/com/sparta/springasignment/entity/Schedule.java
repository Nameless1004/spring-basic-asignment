package com.sparta.springasignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private Long scheduleId;
    private Long managerId;
    private String password;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
