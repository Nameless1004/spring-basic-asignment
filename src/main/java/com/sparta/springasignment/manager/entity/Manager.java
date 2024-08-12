package com.sparta.springasignment.manager.entity;


import com.sparta.springasignment.common.interfaces.Entity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager implements Entity {

  private Long id;
  private String name;
  private String email;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;
}
