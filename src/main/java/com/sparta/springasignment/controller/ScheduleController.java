package com.sparta.springasignment.controller;

import com.sparta.springasignment.ScheduleManagementService;
import com.sparta.springasignment.dto.ManagerRequestDto;
import com.sparta.springasignment.dto.ManagerResponseDto;
import com.sparta.springasignment.dto.ScheduleRequestDto;
import com.sparta.springasignment.dto.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleManagementService service;

    @PostMapping("/schedules")
    @ResponseBody
    public ScheduleResponseDto postSchedule(@RequestBody ScheduleRequestDto dto){
        ScheduleResponseDto save = service.save(dto);
        return save;
    }


    @PostMapping("/managers")
    @ResponseBody
    public ManagerResponseDto postManager(@RequestBody ManagerRequestDto dto){
        ManagerResponseDto save = service.save(dto);
        return save;
    }

    @GetMapping("/managers/{id}")
    @ResponseBody
    public ManagerResponseDto getManager(@PathVariable Long id){
        return service.findManagerById(id);
    }
}
