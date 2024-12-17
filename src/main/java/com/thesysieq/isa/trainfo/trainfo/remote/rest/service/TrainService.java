package com.thesysieq.isa.trainfo.trainfo.remote.rest.service;

import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private final TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<TrainEntity> findAll() {
        return trainRepository.findAll();
    }

    public TrainEntity save(TrainEntity train) {
        return trainRepository.save(train);
    }
}
