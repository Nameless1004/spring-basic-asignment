package com.sparta.springasignment.schedule.service;

import com.sparta.springasignment.common.exception.InvalidIdException;
import com.sparta.springasignment.common.exception.MissmatchPasswordException;
import com.sparta.springasignment.schedule.dto.ScheduleDeleteDto;
import com.sparta.springasignment.schedule.dto.ScheduleRequestDto;
import com.sparta.springasignment.schedule.dto.ScheduleResponseDto;
import com.sparta.springasignment.schedule.entity.Schedule;
import com.sparta.springasignment.schedule.repository.ScheduleRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    // DB 저장
    public ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = Schedule.builder()
            .managerId(scheduleRequestDto.getManagerId())
            .password(scheduleRequestDto.getPassword())
            .contents(scheduleRequestDto.getContents())
            .createdTime(LocalDateTime.now())
            .updatedTime(LocalDateTime.now())
            .build();

        Long id = repository.save(schedule);

        schedule.setScheduleId(id);
        return ScheduleResponseDto.builder()
            .scheduleId(schedule.getScheduleId())
            .managerId(schedule.getManagerId())
            .password(schedule.getPassword())
            .contents(schedule.getContents())
            .createdTime(schedule.getCreatedTime())
            .updatedTime(schedule.getUpdatedTime())
            .build();
    }

    // 업데이트
    public ScheduleResponseDto updateSchedule(Long scheduleId,
        ScheduleRequestDto updateRequestDto) {

        Schedule target = repository.findById(scheduleId)
            .orElseThrow(() -> new InvalidIdException(scheduleId));

        if (!target.getPassword()
            .equals(updateRequestDto.getPassword())) {
            throw new MissmatchPasswordException();
        }

        target.setUpdatedTime(LocalDateTime.now());
        target.setContents(updateRequestDto.getContents());
        target.setManagerId(updateRequestDto.getManagerId());
        repository.update(target);

        return ScheduleResponseDto.builder()
            .scheduleId(target.getScheduleId())
            .managerId(target.getManagerId())
            .password(target.getPassword())
            .contents(target.getContents())
            .createdTime(target.getCreatedTime())
            .updatedTime(target.getUpdatedTime())
            .build();
    }

    // 삭제
    public ScheduleResponseDto delete(Long id, ScheduleDeleteDto deleteDto) {
        Schedule deleted = repository.findById(id)
            .orElseThrow(() -> new InvalidIdException(id));

        if (!deleted.getPassword()
            .equals(deleteDto.getPassword())) {
            throw new MissmatchPasswordException();
        }

        repository.delete(deleted);

        return ScheduleResponseDto.builder()
            .scheduleId(deleted.getScheduleId())
            .managerId(deleted.getManagerId())
            .password(deleted.getPassword())
            .contents(deleted.getContents())
            .createdTime(deleted.getCreatedTime())
            .updatedTime(deleted.getUpdatedTime())
            .build();
    }

    // 다건 조회
    public List<ScheduleResponseDto> findAllSchedules(String updatedTime, Long managerId, Long page,
        Long pageSize) {

        List<Schedule> allManager = repository.findAll(updatedTime, managerId, page,
            pageSize);
        return allManager.stream()
            .map(schedule -> {
                return ScheduleResponseDto.builder()
                    .scheduleId(schedule.getScheduleId())
                    .managerId(schedule.getManagerId())
                    .password(schedule.getPassword())
                    .contents(schedule.getContents())
                    .createdTime(schedule.getCreatedTime())
                    .updatedTime(schedule.getUpdatedTime())
                    .build();
            })
            .toList();
    }

    // 단건 조회
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = repository.findById(id)
            .orElseThrow(() -> new InvalidIdException(id));

        return ScheduleResponseDto.builder()
            .scheduleId(schedule.getScheduleId())
            .managerId(schedule.getManagerId())
            .password(schedule.getPassword())
            .contents(schedule.getContents())
            .createdTime(schedule.getCreatedTime())
            .updatedTime(schedule.getUpdatedTime())
            .build();
    }
}
