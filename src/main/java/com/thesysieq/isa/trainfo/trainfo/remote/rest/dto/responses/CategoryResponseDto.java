package com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.responses;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.domain.model.CategoryType;
import lombok.Value;

import java.util.UUID;

@Value
public class CategoryResponseDto {
    UUID categoryId;
    CategoryType categoryType;
    String businessName;
    String operatorName;
    Float pricePerKmPLN;

    public static CategoryResponseDto transferToDto(CategoryEntity category) {
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getCategoryType(),
                category.getBusinessName(),
                category.getOperatorName(),
                category.getPricePerKmPLN()
        );
    }
}
