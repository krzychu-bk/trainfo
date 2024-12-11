package com.thesysieq.isa.trainfo.trainfo.remote.rest.repository;

import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainRepository extends JpaRepository<TrainEntity, UUID> {
}
