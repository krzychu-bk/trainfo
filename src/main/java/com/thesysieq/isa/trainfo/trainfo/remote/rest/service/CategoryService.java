package com.thesysieq.isa.trainfo.trainfo.remote.rest.service;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryEntity findById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }

    public void delete(CategoryEntity category) {
        categoryRepository.delete(category);
    }
}
