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
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule(@Positive @RequestParam(required = false) Long id, @RequestParam(required = false)@Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$") String updatedTime){
        return new ResponseEntity<>(service.findAllSchedules(updatedTime, id), HttpStatus.OK);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@Positive @PathVariable Long id){
        return new ResponseEntity<>(service.findScheduleById(id), HttpStatus.OK);
    }

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> postSchedule(@Valid @RequestBody ScheduleRequestDto dto){
        ScheduleResponseDto save = service.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> putSchedule(@Positive @PathVariable Long id, @Size(min = 1, max = 30) @NotBlank @RequestParam String password, @Valid @RequestBody ScheduleUpdateRequestDto dto){
        ScheduleResponseDto scheduleResponseDto = service.updateSchedule(id, dto, password);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity deleteSchedule(@Positive @PathVariable Long id, @RequestParam @NotBlank  String password){
        service.delete(id, password);
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
