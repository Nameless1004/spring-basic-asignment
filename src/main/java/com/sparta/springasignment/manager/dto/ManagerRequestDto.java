package com.sparta.springasignment.manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
