package com.sparta.springasignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {
    @NotNull
    @Positive
    private Long managerId;
    @NotBlank
    @Size(min = 1, max = 200)
    private String contents;
}
