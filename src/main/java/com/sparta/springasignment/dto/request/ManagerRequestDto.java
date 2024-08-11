package com.sparta.springasignment.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ManagerRequestDto {
    @NotBlank
    @Size(min = 1, max = 40)
    private String name;
    @Email
    @Size(min = 1, max = 40)
    private String email;
}
