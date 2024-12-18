package com.thesysieq.isa.trainfo.trainfo.remote.rest.controller;

import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.responses.StopResponseDto;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.StopService;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//public class StopController {
//    private final StopService stopService;
//    private final TrainService trainService;
//
//    @Autowired
//    public StopController(StopService stopService, TrainService trainService) {
//        this.stopService = stopService;
//        this.trainService = trainService;
//    }
//
//    @GetMapping("/stops/")
//    public ResponseEntity<List<StopResponseDto>> getAllStops() {
//
//    }
//}
