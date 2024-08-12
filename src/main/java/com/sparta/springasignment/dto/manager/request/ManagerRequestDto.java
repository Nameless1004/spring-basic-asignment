package com.sparta.springasignment.dto.manager.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ManagerRequestDto {
    @NotBlank
    @Size(min = 1, max = 40)
    private String name;
    @Email
    @Size(min = 1, max = 40)
    private String email;
}
