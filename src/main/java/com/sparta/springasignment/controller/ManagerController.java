package com.sparta.springasignment.controller;

import com.sparta.springasignment.dto.ManagerRequestDto;
import com.sparta.springasignment.dto.ManagerResponseDto;
import com.sparta.springasignment.dto.ManagerUpdateRequestDto;
import com.sparta.springasignment.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService service;

    @GetMapping("/managers")
    @ResponseBody
    public List<ManagerResponseDto> getAllManagers(){
        return service.findAllManagers();
    }


    @GetMapping("/managers/{id}")
    @ResponseBody
    public ManagerResponseDto getManager(@PathVariable Long id){
        return service.findManagerById(id);
    }

    @PostMapping("/managers")
    @ResponseBody
    public ManagerResponseDto postManager(@RequestBody ManagerRequestDto dto){
        ManagerResponseDto save = service.save(dto);
        return save;
    }

    @PutMapping("/managers/{id}")
    @ResponseBody
    public ManagerResponseDto updateManager(@PathVariable Long id, @RequestBody ManagerUpdateRequestDto dto){
        return service.updateManager(id, dto);
    }

    @DeleteMapping("/managers/{id}")
    public void deleteManager(@PathVariable Long id){
        service.delete(id);
    }
}
