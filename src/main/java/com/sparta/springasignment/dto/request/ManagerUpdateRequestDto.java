package com.sparta.springasignment.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerUpdateRequestDto {
    @NotBlank
    @Size(min = 1, max = 40)
    private String name;
    @Email
    @Size(min = 1, max = 40)
    private String email;
}
