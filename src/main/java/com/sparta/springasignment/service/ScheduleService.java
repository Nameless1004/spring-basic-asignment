package com.sparta.springasignment.service;

import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.dto.ScheduleResponseDto;
import com.sparta.springasignment.dto.ScheduleUpdateRequestDto;
import com.sparta.springasignment.entity.Schedule;
import com.sparta.springasignment.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;


    // Update
    public ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto updateRequestDto)
    {
        Optional<Schedule> targetOp = repository.findScheduleById(updateRequestDto.getScheduleId());
        if(targetOp.isPresent()){
            Schedule target = targetOp.get();
            target.setUpdatedTime(LocalDateTime.now());
            target.setContents(updateRequestDto.getContents());
            target.setManagerId(updateRequestDto.getManagerId());
            repository.update(target);

            ScheduleResponseDto ret = new ScheduleResponseDto(target.getScheduleId(), target.getManagerId(), target.getPassword(), target.getContents(), target.getCreatedTime(), target.getUpdatedTime());
            return ret;
        } else{
            throw new IllegalArgumentException("존재하지 않는 id입니다.");
        }
    }

    // 다 건 조회
    public List<ScheduleResponseDto> findAllSchedules(String updateTime, Long managerId) {
        String sql = "select * from schedules";

            try {
                if (!updateTime.isEmpty() && managerId == -1) {
                    sql += MessageFormat.format(" where DATE_FORMAT(updated_time, ''%Y-%m-%d'') in (''{0}'')", updateTime);
                } else if (updateTime.isEmpty() && managerId != -1) {
                    sql += " where manager_id = " + managerId;
                } else if(updateTime.isEmpty()==false && managerId != -1){
                    sql += MessageFormat.format(" where DATE_FORMAT(updated_time, ''%Y-%m-%d'') in (''{0}'')", updateTime) + " and manager_id = " + managerId;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("날짜 조건 형식에 맞지 않음(YYYY-MM-DD)");
                return new ArrayList<>();
            }

        sql += " order by updated_time desc";

        List<Schedule> allManager = repository.findAllSchedulesByQuery(sql);
        var result = allManager.stream().map(x -> {
            ScheduleResponseDto dto = new ScheduleResponseDto(x.getScheduleId(), x.getManagerId(), x.getPassword(), x.getContents(), x.getCreatedTime(), x.getUpdatedTime());
            return dto;
        }).toList();
        return result;
    }

    // DB 저장
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

    // 단 건 조회
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

    // 삭제
    public ScheduleResponseDto delete(Long id, String password) {
        Optional<Schedule> find = repository.findScheduleById(id);
        if (find.isPresent()) {
            Schedule deleted = find.get();
            repository.delete(id);
            ScheduleResponseDto deletedManager = new ScheduleResponseDto(deleted.getScheduleId(), deleted.getManagerId(), deleted.getPassword(), deleted.getContents(), deleted.getCreatedTime(), deleted.getUpdatedTime());
            return deletedManager;
        } else {
            throw new IllegalArgumentException("존재하지 않는 id 입니다.");
        }
    }
}
