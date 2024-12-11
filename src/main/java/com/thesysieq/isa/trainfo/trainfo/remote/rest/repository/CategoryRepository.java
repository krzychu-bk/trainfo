package com.thesysieq.isa.trainfo.trainfo.remote.rest.repository;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
