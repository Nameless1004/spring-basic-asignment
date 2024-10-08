package com.sparta.springasignment.manager.service;

import com.sparta.springasignment.common.exception.InvalidIdException;
import com.sparta.springasignment.manager.dto.ManagerRequestDto;
import com.sparta.springasignment.manager.dto.ManagerResponseDto;
import com.sparta.springasignment.manager.entity.Manager;
import com.sparta.springasignment.manager.repository.ManagerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository repository;

    // DB 저장
    public ManagerResponseDto save(ManagerRequestDto managerDto) {
        Manager manager = Manager.builder()
            .name(managerDto.getName())
            .email(managerDto.getEmail())
            .createdTime(LocalDateTime.now())
            .updatedTime(LocalDateTime.now())
            .build();

        Long id = repository.save(manager);

        manager.setId(id);

        return ManagerResponseDto.builder()
            .id(manager.getId())
            .name(manager.getName())
            .email(manager.getEmail())
            .createdTime(manager.getCreatedTime())
            .updatedTime(manager.getUpdatedTime())
            .build();
    }

    // 업데이트
    public ManagerResponseDto updateManager(Long id, ManagerRequestDto managerUpdateDto) {
        Optional<Manager> managerById = repository.findById(id);
        Manager manager = repository.findById(id)
            .orElseThrow(() -> new InvalidIdException(id));

        manager.setUpdatedTime(LocalDateTime.now());
        manager.setName(managerUpdateDto.getName());
        manager.setEmail(managerUpdateDto.getEmail());

        repository.update(manager);

        return ManagerResponseDto.builder()
            .id(manager.getId())
            .name(manager.getName())
            .email(manager.getEmail())
            .createdTime(manager.getCreatedTime())
            .updatedTime(manager.getUpdatedTime())
            .build();
    }

    // 삭제
    public ManagerResponseDto delete(Long managerId) {
        Manager deleted = repository.findById(managerId)
            .orElseThrow(() -> new InvalidIdException(managerId));

        repository.delete(deleted);

        return ManagerResponseDto.builder()
            .id(deleted.getId())
            .name(deleted.getName())
            .email(deleted.getEmail())
            .createdTime(deleted.getCreatedTime())
            .updatedTime(deleted.getUpdatedTime())
            .build();

    }

    // 다건 조회
    public List<ManagerResponseDto> findAllManagers() {
        List<Manager> allManager = repository.findAll();

        return allManager.stream()
            .map(manager -> {
                return ManagerResponseDto.builder()
                    .id(manager.getId())
                    .name(manager.getName())
                    .email(manager.getEmail())
                    .createdTime(manager.getCreatedTime())
                    .updatedTime(manager.getUpdatedTime())
                    .build();
            })
            .toList();
    }


    // 단건 조회
    public ManagerResponseDto findManagerById(Long id) {
        Manager manager = repository.findById(id)
            .orElseThrow(() -> new InvalidIdException(id));

        return ManagerResponseDto.builder()
            .id(manager.getId())
            .name(manager.getName())
            .email(manager.getEmail())
            .createdTime(manager.getCreatedTime())
            .updatedTime(manager.getUpdatedTime())
            .build();
    }
}
