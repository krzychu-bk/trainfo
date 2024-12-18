package com.thesysieq.isa.trainfo.trainfo.remote.rest.controller;

import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.requests.TrainRequestDto;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.responses.TrainResponseDto;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.CategoryService;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.TrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/categories/{category_id}/trains/")
    public ResponseEntity<List<TrainResponseDto>> getTrainsByCategory(@PathVariable UUID category_id) {
        var category = categoryService.findById(category_id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.getTrains().stream().map(TrainResponseDto::transferToDto).toList(), HttpStatus.OK);
    }

    @PutMapping("/categories/{category_id}/trains/{train_id}")
    public ResponseEntity<TrainResponseDto> updateTrain(@PathVariable UUID category_id, @PathVariable UUID train_id , @RequestBody TrainRequestDto trainRequestDto) {
        var train = trainService.findById(train_id);
        var category = categoryService.findById(category_id);
        var status = HttpStatus.OK;
        if (train == null && category != null) {
            train = TrainEntity.builder()
                    .trainId(train_id)
                    .trainNumber(trainRequestDto.getTrainNumber())
                    .category(category)
                    .build();
            status = HttpStatus.CREATED;
            category.getTrains().add(train);
            categoryService.save(category);
        }
        else if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            train.setTrainNumber(trainRequestDto.getTrainNumber());
        }
        trainService.save(train);
        return new ResponseEntity<>(TrainResponseDto.transferToDto(train), status);
    }

    @DeleteMapping("/trains/{uuid}")
    public ResponseEntity<TrainResponseDto> deleteTrain(@PathVariable UUID uuid) {
        var train = trainService.findById(uuid);
        if (train == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainService.delete(train);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
