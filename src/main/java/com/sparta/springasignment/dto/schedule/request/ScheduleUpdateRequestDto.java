package com.sparta.springasignment.dto.schedule.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateRequestDto {

  @NotNull
  @Positive
  private Long managerId;

  @NotBlank
  @Size(min = 1, max = 200)
  private String contents;

  @NotBlank
  private String password;
}
