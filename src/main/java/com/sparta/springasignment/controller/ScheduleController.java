package com.sparta.springasignment.controller;

import com.sparta.springasignment.dto.ScheduleUpdateRequestDto;
import com.sparta.springasignment.service.ScheduleService;
import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.dto.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("/schedules")
    @ResponseBody
    public List<ScheduleResponseDto> getAllSchedule(@RequestParam(defaultValue = "-1")Long id, @RequestParam(defaultValue = "") String updatedTime){
        return service.findAllSchedules(updatedTime, id);
    }

    @GetMapping("/schedules/{id}")
    @ResponseBody
    public ScheduleResponseDto getSchedule(@PathVariable Long id){
        return service.findScheduleById(id);
    }

    @PostMapping("/schedules")
    @ResponseBody
    public ScheduleResponseDto postSchedule(@RequestBody ScheduleRequestDto dto){
        ScheduleResponseDto save = service.save(dto);
        return save;
    }

    @PutMapping("/schedules")
    @ResponseBody
    public ScheduleResponseDto putSchedule(@RequestBody ScheduleUpdateRequestDto dto){
        ScheduleResponseDto scheduleResponseDto = service.updateSchedule(dto);
        return scheduleResponseDto;
    }

    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestParam String password){
        service.delete(id, password);
    }

}
