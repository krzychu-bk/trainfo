package com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.requests;

import com.thesysieq.isa.trainfo.trainfo.domain.model.CategoryType;
import lombok.Value;

@Value
public class CategoryRequestDto {
    CategoryType categoryType;
    String businessName;
    String operatorName;
    Float pricePerKmPLN;
}
