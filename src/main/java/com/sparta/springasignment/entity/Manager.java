package com.sparta.springasignment.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager implements  Entity{
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
