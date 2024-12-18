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

    @GetMapping("/categories/{uuid}/trains/")
    public ResponseEntity<List<TrainResponseDto>> getCategories(@PathVariable UUID uuid) {
        var category = categoryService.findById(uuid);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.getTrains().stream().map(TrainResponseDto::transferToDto).toList(), HttpStatus.OK);
    }

    @PostMapping("/categories/{uuid}/trains/{trainId}")
    public ResponseEntity<TrainResponseDto> createTrain(@PathVariable UUID uuid, @PathVariable UUID trainId, @RequestBody TrainRequestDto trainRequestDto) {
        var category = categoryService.findById(uuid);

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var trainToPost = TrainEntity.builder()
                .trainId(trainId)
                .trainNumber(trainRequestDto.getTrainNumber())
                .category(category)
                .build();
        trainService.save(trainToPost);
        return new ResponseEntity<>(TrainResponseDto.transferToDto(trainToPost), HttpStatus.CREATED);
    }

    @PutMapping("/genres/{uuid}/games")
    public ResponseEntity<TrainResponseDto> updateTrain(@PathVariable UUID uuid, @RequestBody TrainRequestDto trainRequestDto) {
        var train = trainService.findById(uuid);
        if (train == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        train.setTrainNumber(trainRequestDto.getTrainNumber());
        trainService.save(train);
        return new ResponseEntity<>(TrainResponseDto.transferToDto(train), HttpStatus.OK);
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
