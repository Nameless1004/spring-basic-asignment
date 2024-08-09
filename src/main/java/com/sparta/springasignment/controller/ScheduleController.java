package com.sparta.springasignment.controller;

import com.sparta.springasignment.ScheduleManagementService;
import com.sparta.springasignment.dto.ManagerRequestDto;
import com.sparta.springasignment.dto.ScheduleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleManagementService service;

    @PostMapping("/schedule")
    @ResponseBody
    public String postingSchedule(){
        return "";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        service.save(new ManagerRequestDto("testname", "daf@namver.com", LocalDateTime.now(),LocalDateTime.now()));
        service.save(new ScheduleRequestDto(1L, "123","123", LocalDateTime.now(),LocalDateTime.now()));
        //var result = service.findById(2L);
        return "";
    }
}
