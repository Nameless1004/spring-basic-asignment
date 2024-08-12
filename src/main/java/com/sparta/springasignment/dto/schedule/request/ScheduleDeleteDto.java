package com.sparta.springasignment.dto.schedule.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDeleteDto {
    @NotBlank
    @Size(min = 1, max = 20)
    private String password;
}
