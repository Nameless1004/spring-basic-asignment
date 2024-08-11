package com.sparta.springasignment.service;

import com.sparta.springasignment.common.exception.MissmatchPasswordException;
import com.sparta.springasignment.dto.request.ScheduleRequestDto;
import com.sparta.springasignment.dto.response.ScheduleResponseDto;
import com.sparta.springasignment.dto.request.ScheduleUpdateRequestDto;
import com.sparta.springasignment.entity.Schedule;
import com.sparta.springasignment.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    // DB 저장
    public ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule();
        schedule.setManagerId(scheduleRequestDto.getManagerId());
        schedule.setPassword(scheduleRequestDto.getPassword());
        schedule.setContents(scheduleRequestDto.getContents());
        schedule.setCreatedTime(LocalDateTime.now());
        schedule.setUpdatedTime(LocalDateTime.now());

        Long id = repository.save(schedule);

        schedule.setScheduleId(id);
        ScheduleResponseDto responseManagerDto = new ScheduleResponseDto(schedule.getScheduleId(), schedule.getManagerId(), schedule.getPassword(), schedule.getContents(), schedule.getCreatedTime(), schedule.getUpdatedTime());
        return responseManagerDto;
    }

    // 업데이트
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto updateRequestDto, String password) {
        Optional<Schedule> targetOp = repository.findScheduleById(scheduleId);
        if (targetOp.isPresent()) {
            Schedule target = targetOp.get();
            if (!target.getPassword().equals(password)) {
                throw new MissmatchPasswordException("비밀번호가 일치하지 않습니다.");
            }
            target.setUpdatedTime(LocalDateTime.now());
            target.setContents(updateRequestDto.getContents());
            target.setManagerId(updateRequestDto.getManagerId());
            repository.update(target);

            ScheduleResponseDto ret = new ScheduleResponseDto(target.getScheduleId(), target.getManagerId(), target.getPassword(), target.getContents(), target.getCreatedTime(), target.getUpdatedTime());
            return ret;
        } else {
            throw new IllegalArgumentException("존재하지 않는 id입니다.");
        }
    }

    // 삭제
    public ScheduleResponseDto delete(Long id, String password) {
        Optional<Schedule> find = repository.findScheduleById(id);
        if (find.isPresent()) {
            Schedule deleted = find.get();
            if (!deleted.getPassword().equals(password)) {
                throw new MissmatchPasswordException("비밀번호가 일치하지 않습니다.");
            }
            repository.delete(deleted);
            ScheduleResponseDto deletedSchedule = new ScheduleResponseDto(deleted.getScheduleId(), deleted.getManagerId(), deleted.getPassword(), deleted.getContents(), deleted.getCreatedTime(), deleted.getUpdatedTime());
            return deletedSchedule;
        } else {
            throw new IllegalArgumentException("존재하지 않는 id 입니다.");
        }
    }

    // 다건 조회
    public List<ScheduleResponseDto> findAllSchedules(String updatedTime, Long managerId) {
        String sql = "select * from schedules";

        if (updatedTime != null && managerId == null) {
            sql += MessageFormat.format(" where DATE_FORMAT(updated_time, ''%Y-%m-%d'') in (''{0}'')", updatedTime);
        } else if (updatedTime == null && managerId != null) {
            sql += " where manager_id = " + managerId;
        } else if (updatedTime != null && managerId != null) {
            sql += MessageFormat.format(" where DATE_FORMAT(updated_time, ''%Y-%m-%d'') in (''{0}'')", updatedTime) + " and manager_id = " + managerId;
        }

        sql += " order by updated_time desc";

        List<Schedule> allManager = repository.findAllSchedulesByQuery(sql);
        var result = allManager.stream().map(x -> {
            ScheduleResponseDto dto = new ScheduleResponseDto(x.getScheduleId(), x.getManagerId(), x.getPassword(), x.getContents(), x.getCreatedTime(), x.getUpdatedTime());
            return dto;
        }).toList();
        return result;
    }

    // 단건 조회
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> managerById = repository.findScheduleById(id);
        if (managerById.isPresent()) {
            Schedule schedule = managerById.get();
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule.getScheduleId(), schedule.getManagerId(), schedule.getPassword(), schedule.getContents(), schedule.getCreatedTime(), schedule.getUpdatedTime());
            return dto;
        } else {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
    }

    // 페이지 조회
    public List<ScheduleResponseDto> findSchedulsByPage(Integer pageNum, Integer pageSize) {
        List<Schedule> schedulesByPage = repository.findSchedulesByPage(pageNum, pageSize);
        List<ScheduleResponseDto> list = schedulesByPage.stream().map(x -> {
            ScheduleResponseDto dto = new ScheduleResponseDto(x.getScheduleId(), x.getManagerId(), x.getPassword(), x.getContents(), x.getCreatedTime(), x.getUpdatedTime());
            return dto;
        }).toList();

        return list;
    }
}
