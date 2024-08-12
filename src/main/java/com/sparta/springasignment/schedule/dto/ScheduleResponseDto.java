package com.sparta.springasignment.schedule.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ScheduleResponseDto {

  private Long scheduleId;
  private Long managerId;
  private String password;
  private String contents;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;
}
