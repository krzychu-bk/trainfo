package com.thesysieq.isa.trainfo.trainfo.remote.rest.controller;

import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.responses.TrainResponseDto;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.CategoryService;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.TrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TrainController {
    private final CategoryService categoryService;
    private final TrainService trainService;

    public TrainController(CategoryService categoryService, TrainService trainService) {
        this.categoryService = categoryService;
        this.trainService = trainService;
    }

    @GetMapping("/trains/")
    public ResponseEntity<List<TrainResponseDto>> getTrains() {
        return new ResponseEntity<>(trainService.findAll().stream()
                .map(TrainResponseDto::transferToDto)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/trains/{uuid}/")
    public ResponseEntity<TrainResponseDto> getTrain(@PathVariable UUID uuid) {
        var train = trainService.findById(uuid);
        if (train == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(TrainResponseDto.transferToDto(train), HttpStatus.OK);
    }

    @GetMapping("/categories/{uuid}/trains/")
    public ResponseEntity<List<TrainResponseDto>> getCategories(@PathVariable UUID uuid) {
        var category = categoryService.findById(uuid);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.getTrains().stream().map(TrainResponseDto::transferToDto).toList(), HttpStatus.OK);
    }

}
