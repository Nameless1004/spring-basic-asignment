package com.sparta.springasignment.service;

import com.sparta.springasignment.dto.ManagerRequestDto;
import com.sparta.springasignment.dto.ManagerResponseDto;
import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.repository.ManagerRepository;
import com.sparta.springasignment.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository repository;

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

    public void updateManager(Long managerId){
        Optional<Manager> managerById = repository.findManagerById(managerId);
        if(managerById.isPresent()){
            Manager manager = managerById.get();
            //         manager.
        }
        // empty()처리
    }

    public ManagerResponseDto delete(Long managerId){
        Optional<Manager> find = repository.findManagerById(managerId);
        if(find.isPresent()){
            Manager deleted = find.get();
            repository.delete(managerId);
            ManagerResponseDto deletedManager = new ManagerResponseDto(deleted.getId(), deleted.getName(), deleted.getEmail(), deleted.getCreatedTime(), deleted.getUpdatedTime());
            return deletedManager;
        } else {
            throw new IllegalArgumentException("존재하지 않는 id 입니다.");
        }
    }

    public List<ManagerResponseDto> findAllManagers() {
        List<Manager> allManager = repository.findAllManagers();
        return allManager.stream()
                .map(x->{
                    ManagerResponseDto dto = new ManagerResponseDto(x.getId(), x.getName(), x.getEmail(), x.getCreatedTime(), x.getUpdatedTime());
                    return dto;
                }).toList();
    }


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
}
