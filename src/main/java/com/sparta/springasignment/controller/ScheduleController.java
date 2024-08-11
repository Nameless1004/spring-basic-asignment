package com.sparta.springasignment.controller;

import com.sparta.springasignment.dto.request.ScheduleRequestDto;
import com.sparta.springasignment.dto.response.ScheduleResponseDto;
import com.sparta.springasignment.dto.request.ScheduleUpdateRequestDto;
import com.sparta.springasignment.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule(@Positive @RequestParam(required = false) Long managerId, @RequestParam(required = false)@Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$") String updatedTime){
        return new ResponseEntity<>(service.findAllSchedules(updatedTime, managerId), HttpStatus.OK);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@Positive @PathVariable Long scheduleId){
        return new ResponseEntity<>(service.findScheduleById(scheduleId), HttpStatus.OK);
    }

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> postSchedule(@Valid @RequestBody ScheduleRequestDto dto){
        ScheduleResponseDto save = service.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> putSchedule(@Positive @PathVariable Long scheduleId, @Size(min = 1, max = 30) @NotBlank @RequestParam @Size(min = 1, max = 20) String password, @Valid @RequestBody ScheduleUpdateRequestDto dto){
        ScheduleResponseDto scheduleResponseDto = service.updateSchedule(scheduleId, dto, password);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity deleteSchedule(@Positive @PathVariable Long scheduleId, @RequestParam @NotBlank @Size(min = 1, max = 20) String password){
        service.delete(scheduleId, password);
        return ResponseEntity.ok().build();
    }

    // page
    @GetMapping("/schedules/")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedulesByPage(@Min(1) @RequestParam(defaultValue = "1") Integer page, @Min(1) @RequestParam(defaultValue = "1") Integer size){
        List<ScheduleResponseDto> schedulsByPage = service.findSchedulsByPage(page, size);
        ResponseEntity<List<ScheduleResponseDto>> response = new ResponseEntity<>(schedulsByPage, HttpStatus.OK);
        return response;
    }

}
