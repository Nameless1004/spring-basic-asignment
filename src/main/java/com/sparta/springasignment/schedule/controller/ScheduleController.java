package com.sparta.springasignment.schedule.controller;

import com.sparta.springasignment.schedule.dto.ScheduleDeleteDto;
import com.sparta.springasignment.schedule.dto.ScheduleRequestDto;
import com.sparta.springasignment.schedule.dto.ScheduleResponseDto;
import com.sparta.springasignment.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
@Validated
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule(
        @Positive @RequestParam(required = false) Long managerId,
        @RequestParam(required = false) @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$") String updatedTime,
        @RequestParam @Min(1) Long page, @RequestParam @Min(1) Long pageSize) {
        return new ResponseEntity<>(
            service.findAllSchedules(updatedTime, managerId, page, pageSize),
            HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(
        @Positive @PathVariable Long scheduleId) {
        return new ResponseEntity<>(service.findScheduleById(scheduleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> postSchedule(
        @Valid @RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto save = service.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> putSchedule(@Positive @PathVariable Long scheduleId,
        @Valid @RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto = service.updateSchedule(scheduleId, dto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@Positive @PathVariable Long scheduleId,
        @Valid @RequestBody ScheduleDeleteDto dto) {
        service.delete(scheduleId, dto);
        return ResponseEntity.ok()
            .build();
    }

}
