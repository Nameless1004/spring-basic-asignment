package com.sparta.springasignment;

import com.sparta.springasignment.dto.ManagerRequestDto;
import com.sparta.springasignment.dto.ManagerResponseDto;
import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.dto.ScheduleResponseDto;
import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.entity.Schedule;
import com.sparta.springasignment.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalLong;

@Service
@RequiredArgsConstructor
public class ScheduleManagementService {

    private final ScheduleRepository repository;

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
    public void updateSchedule(Long scheduleId) {
        Optional<Schedule> scheduleById = repository.findScheduleById(scheduleId);
        // empty()처리
    }
    public void updateManager(Long managerId){
        Optional<Manager> managerById = repository.findManagerById(managerId);
        if(managerById.isPresent()){
            Manager manager = managerById.get();
   //         manager.
        }
        // empty()처리
    }

    public ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule();
        schedule.setManagerId(scheduleRequestDto.getManagerId());
        schedule.setPassword(scheduleRequestDto.getPassword());
        schedule.setContents(scheduleRequestDto.getContents());
        schedule.setCreatedTime(scheduleRequestDto.getCreatedTime());
        schedule.setUpdatedTime(scheduleRequestDto.getUpdatedTime());

        Long id = repository.save(schedule);

        schedule.setScheduleId(id);
        ScheduleResponseDto responseManagerDto = new ScheduleResponseDto(schedule.getScheduleId(), schedule.getManagerId(), schedule.getPassword(), schedule.getContents(), schedule.getCreatedTime(), schedule.getUpdatedTime());
        return responseManagerDto;
    }
}
