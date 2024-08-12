package com.sparta.springasignment.manager.controller;

import com.sparta.springasignment.manager.dto.ManagerRequestDto;
import com.sparta.springasignment.manager.dto.ManagerResponseDto;
import com.sparta.springasignment.manager.dto.ManagerUpdateRequestDto;
import com.sparta.springasignment.manager.service.ManagerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class ManagerController {

  private final ManagerService service;

  @GetMapping("/managers")
  public ResponseEntity<List<ManagerResponseDto>> getAllManagers() {
    return new ResponseEntity<>(service.findAllManagers(), HttpStatus.OK);
  }

  @GetMapping("/managers/{managerId}")
  public ResponseEntity<ManagerResponseDto> getManager(@Positive @PathVariable Long managerId) {
    return new ResponseEntity<>(service.findManagerById(managerId), HttpStatus.OK);
  }

  @PostMapping("/managers")
  public ResponseEntity<ManagerResponseDto> postManager(@Valid @RequestBody ManagerRequestDto dto) {
    ManagerResponseDto save = service.save(dto);
    return new ResponseEntity<>(save, HttpStatus.CREATED);
  }

  @PutMapping("/managers/{managerId}")
  public ResponseEntity<ManagerResponseDto> updateManager(@Positive @PathVariable Long managerId,
      @Valid @RequestBody ManagerUpdateRequestDto dto) {
    ManagerResponseDto responseDto = service.updateManager(managerId, dto);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping("/managers/{managerId}")
  public ResponseEntity deleteManager(@Positive @PathVariable Long managerId) {
    service.delete(managerId);
    return ResponseEntity.ok().build();
  }

}
