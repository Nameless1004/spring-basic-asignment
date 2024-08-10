package com.sparta.springasignment.controller;

import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.dto.ScheduleResponseDto;
import com.sparta.springasignment.dto.ScheduleUpdateRequestDto;
import com.sparta.springasignment.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule(@RequestParam(defaultValue = "-1")Long id, @RequestParam(defaultValue = "") String updatedTime){
        return new ResponseEntity<>(service.findAllSchedules(updatedTime, id), HttpStatus.OK);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id){
        return new ResponseEntity<>(service.findScheduleById(id), HttpStatus.OK);
    }

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> postSchedule(@RequestBody ScheduleRequestDto dto){
        ScheduleResponseDto save = service.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> putSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto dto){
        ScheduleResponseDto scheduleResponseDto = service.updateSchedule(scheduleId, dto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity deleteSchedule(@PathVariable Long id, @RequestParam String password){
        service.delete(id, password);
        return ResponseEntity.ok().build();
    }

}
