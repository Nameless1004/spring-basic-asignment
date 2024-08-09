package com.sparta.springasignment.controller;

import com.sparta.springasignment.service.ScheduleService;
import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.dto.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("/schedules")
    @ResponseBody
    public List<ScheduleResponseDto> getAllSchedule(){
        return service.findAllSchedules();
    }

    @GetMapping("/schedules/{id}")
    @ResponseBody
    public ScheduleResponseDto getAllSchedule(@PathVariable Long id){
        return service.findScheduleById(id);
    }

    @PostMapping("/schedules")
    @ResponseBody
    public ScheduleResponseDto postSchedule(@RequestBody ScheduleRequestDto dto){
        ScheduleResponseDto save = service.save(dto);
        return save;
    }

    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestParam String password){
        service.delete(id, password);
    }

}
