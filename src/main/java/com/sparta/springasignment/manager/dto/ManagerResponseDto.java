package com.sparta.springasignment.manager.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ManagerResponseDto {

  private Long id;
  private String name;
  private String email;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;
}
