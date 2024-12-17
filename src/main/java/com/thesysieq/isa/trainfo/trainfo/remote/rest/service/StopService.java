package com.thesysieq.isa.trainfo.trainfo.remote.rest.service;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.StopEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {
    private final StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<StopEntity> findAll() {
        return stopRepository.findAll();
    }

    public List<StopEntity> findByTrain(TrainEntity train) {
        return stopRepository.findByTrain(train);
    }
    public StopEntity save(StopEntity entry) {
        return stopRepository.save(entry);
    }
    public void delete(StopEntity entry) {
        stopRepository.delete(entry);
    }
}
