package com.sparta.springasignment.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule implements Entity {

  @Setter
  private Long scheduleId;
  @Setter
  private Long managerId;

  private String password;
  @Setter
  private String contents;
  private LocalDateTime createdTime;
  @Setter
  private LocalDateTime updatedTime;
}
