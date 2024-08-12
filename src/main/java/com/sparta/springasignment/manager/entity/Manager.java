package com.sparta.springasignment.manager.entity;


import com.sparta.springasignment.common.interfaces.Entity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager implements Entity {

  @Setter
  private Long id;

  @Setter
  private String name;

  @Setter
  private String email;
  private LocalDateTime createdTime;

  @Setter
  private LocalDateTime updatedTime;
}
