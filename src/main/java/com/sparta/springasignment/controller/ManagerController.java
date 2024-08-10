package com.sparta.springasignment.controller;

import com.sparta.springasignment.dto.ManagerRequestDto;
import com.sparta.springasignment.dto.ManagerResponseDto;
import com.sparta.springasignment.dto.ManagerUpdateRequestDto;
import com.sparta.springasignment.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;

    @GetMapping("/managers")
    public ResponseEntity<List<ManagerResponseDto>> getAllManagers(){
        return new ResponseEntity<>(service.findAllManagers(), HttpStatus.OK);
    }

    @GetMapping("/managers/{id}")
    public ResponseEntity<ManagerResponseDto> getManager(@PathVariable Long id){
        return new ResponseEntity<>(service.findManagerById(id), HttpStatus.OK);
    }

    @PostMapping("/managers")
    public ResponseEntity<ManagerResponseDto> postManager(@RequestBody ManagerRequestDto dto){
        ManagerResponseDto save = service.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/managers/{id}")
    public ResponseEntity<ManagerResponseDto> updateManager(@PathVariable Long id, @RequestBody ManagerUpdateRequestDto dto){
        return new ResponseEntity<>(service.updateManager(id, dto),HttpStatus.OK);
    }

    @DeleteMapping("/managers/{id}")
    public ResponseEntity deleteManager(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
