package com.thesysieq.isa.trainfo.trainfo.remote.rest.repository;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StopRepository extends JpaRepository<StopEntity, UUID> {
    List<StopEntity> findByCategory(CategoryEntity category);
}
