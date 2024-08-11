package com.sparta.springasignment.controller;

import com.sparta.springasignment.dto.request.ManagerRequestDto;
import com.sparta.springasignment.dto.response.ManagerResponseDto;
import com.sparta.springasignment.dto.request.ManagerUpdateRequestDto;
import com.sparta.springasignment.service.ManagerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
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
public class ManagerController {

    private final ManagerService service;

    @GetMapping("/managers")
    public ResponseEntity<List<ManagerResponseDto>> getAllManagers(){
        return new ResponseEntity<>(service.findAllManagers(), HttpStatus.OK);
    }

    @GetMapping("/managers/{id}")
    public ResponseEntity<ManagerResponseDto> getManager(@Positive @PathVariable Long id){
        return new ResponseEntity<>(service.findManagerById(id), HttpStatus.OK);
    }

    @PostMapping("/managers")
    public ResponseEntity<ManagerResponseDto> postManager(@Valid @RequestBody ManagerRequestDto dto){
        ManagerResponseDto save = service.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/managers/{id}")
    public ResponseEntity<ManagerResponseDto> updateManager(@Positive @PathVariable Long id, @Valid @RequestBody ManagerUpdateRequestDto dto){
        ManagerResponseDto responseDto = service.updateManager(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/managers/{id}")
    public ResponseEntity deleteManager(@Positive @PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
