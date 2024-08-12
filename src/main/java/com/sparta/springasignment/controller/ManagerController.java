package com.sparta.springasignment.controller;

import com.sparta.springasignment.dto.manager.request.ManagerRequestDto;
import com.sparta.springasignment.dto.manager.response.ManagerResponseDto;
import com.sparta.springasignment.dto.manager.request.ManagerUpdateRequestDto;
import com.sparta.springasignment.service.ManagerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
