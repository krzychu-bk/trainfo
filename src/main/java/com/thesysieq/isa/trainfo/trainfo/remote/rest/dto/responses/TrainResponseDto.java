package com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.responses;

import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import com.thesysieq.isa.trainfo.trainfo.domain.model.CategoryType;
import lombok.Value;

import java.util.UUID;

@Value
public class TrainResponseDto {

    UUID trainId;
    Integer trainNumber;
    CategoryType categoryType;

    public static TrainResponseDto transferToDto(TrainEntity trainEntity) {
        return new TrainResponseDto(
                trainEntity.getTrainId(),
                trainEntity.getTrainNumber(),
                trainEntity.getCategory().getCategoryType()
        );
    }
}
