package com.thesysieq.isa.trainfo.trainfo.remote.rest.controller;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    @GetMapping("/categories/")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll().stream().map(), HttpStatus.OK);
    }
}
