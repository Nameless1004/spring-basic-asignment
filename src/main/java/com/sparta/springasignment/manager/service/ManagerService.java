package com.sparta.springasignment.manager.service;

import com.sparta.springasignment.manager.dto.ManagerRequestDto;
import com.sparta.springasignment.manager.dto.ManagerResponseDto;
import com.sparta.springasignment.manager.dto.ManagerUpdateRequestDto;
import com.sparta.springasignment.manager.entity.Manager;
import com.sparta.springasignment.manager.repository.ManagerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
@RequiredArgsConstructor
public class ManagerService {

  private final ManagerRepository repository;

  // DB 저장
  public ManagerResponseDto save(ManagerRequestDto managerDto) {
    Manager manager = new Manager();
    manager.setName(managerDto.getName());
    manager.setEmail(managerDto.getEmail());
    manager.setCreatedTime(LocalDateTime.now());
    manager.setUpdatedTime(LocalDateTime.now());

    Long id = repository.save(manager);

    manager.setId(id);

    return ManagerResponseDto.builder().id(manager.getId()).name(manager.getName())
        .email(manager.getEmail()).createdTime(manager.getCreatedTime())
        .updatedTime(manager.getUpdatedTime()).build();
  }

  // 업데이트
  public ManagerResponseDto updateManager(Long id, ManagerUpdateRequestDto managerUpdateDto) {
    Optional<Manager> managerById = repository.findById(id);
    Manager manager = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id입니다."));

    manager.setUpdatedTime(LocalDateTime.now());
    manager.setName(managerUpdateDto.getName());
    manager.setEmail(managerUpdateDto.getEmail());

    repository.update(manager);

    return ManagerResponseDto.builder().id(manager.getId()).name(manager.getName())
        .email(manager.getEmail()).createdTime(manager.getCreatedTime())
        .updatedTime(manager.getUpdatedTime()).build();
  }

  // 삭제
  public ManagerResponseDto delete(Long managerId) {
    Manager deleted = repository.findById(managerId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다."));

    repository.delete(deleted);

    return ManagerResponseDto.builder().id(deleted.getId()).name(deleted.getName())
        .email(deleted.getEmail()).createdTime(deleted.getCreatedTime())
        .updatedTime(deleted.getUpdatedTime()).build();

  }

  // 다건 조회
  public List<ManagerResponseDto> findAllManagers() {
    List<Manager> allManager = repository.findAll();

    return allManager.stream().map(manager -> {
      return ManagerResponseDto.builder().id(manager.getId()).name(manager.getName())
          .email(manager.getEmail()).createdTime(manager.getCreatedTime())
          .updatedTime(manager.getUpdatedTime()).build();
    }).toList();
  }


  // 단건 조회
  public ManagerResponseDto findManagerById(Long id) {
    Manager manager = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

    return ManagerResponseDto.builder().id(manager.getId()).name(manager.getName())
        .email(manager.getEmail()).createdTime(manager.getCreatedTime())
        .updatedTime(manager.getUpdatedTime()).build();
  }

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<String> handle(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
