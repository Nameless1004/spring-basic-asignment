package com.sparta.springasignment.service;

import com.sparta.springasignment.dto.request.ManagerRequestDto;
import com.sparta.springasignment.dto.response.ManagerResponseDto;
import com.sparta.springasignment.dto.request.ManagerUpdateRequestDto;
import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        ManagerResponseDto responseManagerDto = new ManagerResponseDto(manager.getId(), manager.getName(), manager.getEmail(), manager.getCreatedTime(), manager.getUpdatedTime());
        return responseManagerDto;
    }

    // 업데이트
    public ManagerResponseDto updateManager(Long id, ManagerUpdateRequestDto managerUpdateDto) {
        Optional<Manager> managerById = repository.findManagerById(id);
        if(managerById.isPresent()){
            Manager manager = managerById.get();

            manager.setUpdatedTime(LocalDateTime.now());
            manager.setName(managerUpdateDto.getName());
            manager.setEmail(managerUpdateDto.getEmail());
            repository.update(manager);
            return new ManagerResponseDto(manager.getId(), manager.getName(), manager.getEmail(), manager.getCreatedTime(), manager.getUpdatedTime());
        } else {
            throw new IllegalArgumentException("존재하지 않는 id입니다.");
        }
    }

    // 삭제
    public ManagerResponseDto delete(Long managerId){
        Optional<Manager> find = repository.findManagerById(managerId);
        if(find.isPresent()){
            Manager deleted = find.get();
            repository.delete(deleted);
            ManagerResponseDto deletedManager = new ManagerResponseDto(deleted.getId(), deleted.getName(), deleted.getEmail(), deleted.getCreatedTime(), deleted.getUpdatedTime());
            return deletedManager;
        } else {
            throw new IllegalArgumentException("존재하지 않는 id 입니다.");
        }
    }

    // 다건 조회
    public List<ManagerResponseDto> findAllManagers() {
        List<Manager> allManager = repository.findAllManagers();
        return allManager.stream()
                .map(x->{
                    ManagerResponseDto dto = new ManagerResponseDto(x.getId(), x.getName(), x.getEmail(), x.getCreatedTime(), x.getUpdatedTime());
                    return dto;
                }).toList();
    }


    // 단건 조회
    public ManagerResponseDto findManagerById(Long id) {
        Optional<Manager> managerById = repository.findManagerById(id);
        if(managerById.isPresent()) {
            Manager manager = managerById.get();
            ManagerResponseDto dto = new ManagerResponseDto(manager.getId(), manager.getName(), manager.getEmail(), manager.getCreatedTime(), manager.getUpdatedTime());
            return dto;
        } else{
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handle(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
