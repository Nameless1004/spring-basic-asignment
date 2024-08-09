package com.sparta.springasignment;

import com.sparta.springasignment.dto.ManagerRequestDto;
import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.entity.Schedule;
import com.sparta.springasignment.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ScheduleManagementService {
    private final ScheduleRepository repository;

    public Schedule findById(Long id) {
        return repository.findScheduleById(id).get();
    }

    public void save(ManagerRequestDto managerDto) {
        Manager manager = new Manager();
        manager.setName(managerDto.getName());
        manager.setEmail(managerDto.getEmail());
        manager.setCreatedTime(managerDto.getCreatedTime());
        manager.setUpdatedTime(managerDto.getUpdatedTime());
        repository.save(manager);
    }

    public void save(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule();
        schedule.setManagerId(scheduleRequestDto.getManagerId());
        schedule.setPassword(scheduleRequestDto.getPassword());
        schedule.setContents(scheduleRequestDto.getContents());
        schedule.setCreatedTime(scheduleRequestDto.getCreatedTime());
        schedule.setUpdatedTime(scheduleRequestDto.getUpdatedTime());
        long id = repository.save(schedule);
        System.out.println(id);
    }
}
