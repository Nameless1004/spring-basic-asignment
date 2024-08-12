package com.sparta.springasignment.dto.schedule.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScheduleRequestDto {
    @NotNull
    @Positive
    private Long managerId;
    @NotBlank
    @Size(min = 1, max = 20)
    private String password;
    @NotBlank
    @Size(min = 1, max = 200)
    private String contents;
}
