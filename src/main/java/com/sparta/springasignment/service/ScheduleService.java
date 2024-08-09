package com.sparta.springasignment.service;

import com.sparta.springasignment.dto.ManagerResponseDto;
import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.dto.ScheduleResponseDto;
import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.entity.Schedule;
import com.sparta.springasignment.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    public void updateSchedule(Long scheduleId) {
        Optional<Schedule> scheduleById = repository.findScheduleById(scheduleId);
        // empty()처리
    }

    public List<ScheduleResponseDto> findAllSchedules(){
        List<Schedule> allManager = repository.findAllSchedules();
        return allManager.stream()
                .map(x->{
                    ScheduleResponseDto dto = new ScheduleResponseDto(x.getScheduleId(), x.getManagerId(), x.getPassword(), x.getContents(), x.getCreatedTime(), x.getUpdatedTime());
                    return dto;
                }).toList();
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

    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> managerById = repository.findScheduleById(id);
        if(managerById.isPresent()) {
            Schedule schedule = managerById.get();
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule.getScheduleId(), schedule.getManagerId(), schedule.getPassword(), schedule.getContents(), schedule.getCreatedTime(), schedule.getUpdatedTime());
            return dto;
        } else{
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
    }

    public ScheduleResponseDto delete(Long scheduleId, String password){
        Optional<Schedule> find = repository.findScheduleById(scheduleId);
        if(find.isPresent()) {
            Schedule deleted = find.get();
            repository.delete(scheduleId);
            ScheduleResponseDto deletedManager = new ScheduleResponseDto(deleted.getScheduleId(), deleted.getManagerId(), deleted.getPassword(), deleted.getContents(), deleted.getCreatedTime(), deleted.getUpdatedTime());
            return deletedManager;
        } else {
            throw  new IllegalArgumentException("존재하지 않는 id 입니다.");
        }
    }
}
