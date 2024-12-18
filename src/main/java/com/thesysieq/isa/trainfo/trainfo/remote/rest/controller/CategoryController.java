package com.thesysieq.isa.trainfo.trainfo.remote.rest.controller;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.requests.CategoryRequestDto;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.responses.CategoryResponseDto;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll().stream()
                .map(CategoryResponseDto::transferToDto)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/categories/{uuid}/")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable UUID uuid) {
        var category = categoryService.findById(uuid);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(CategoryResponseDto.transferToDto(category), HttpStatus.OK);
    }

    @PutMapping("/categories/{uuid}/")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable UUID uuid, @RequestBody CategoryRequestDto categoryRequestDto) {
        var category = categoryService.findById(uuid);
        var status =  HttpStatus.OK;
        if(category == null) {
            category = CategoryEntity.builder()
                    .categoryId(uuid)
                    .categoryType(categoryRequestDto.getCategoryType())
                    .businessName(categoryRequestDto.getBusinessName())
                    .operatorName(categoryRequestDto.getOperatorName())
                    .pricePerKmPLN(categoryRequestDto.getPricePerKmPLN())
                    .build();
            status = HttpStatus.CREATED;
        }
        else{
            category.setCategoryType(categoryRequestDto.getCategoryType());
            category.setBusinessName(categoryRequestDto.getBusinessName());
            category.setOperatorName(categoryRequestDto.getOperatorName());
            category.setPricePerKmPLN(categoryRequestDto.getPricePerKmPLN());
        }
        categoryService.save(category);
        return new ResponseEntity<>(CategoryResponseDto.transferToDto(category), status);
    }

    @DeleteMapping("/categories/{uuid}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable UUID uuid) {
        var category = categoryService.findById(uuid);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(category);
        return new ResponseEntity<>(CategoryResponseDto.transferToDto(category), HttpStatus.NO_CONTENT);
    }
}
