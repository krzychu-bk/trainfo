package com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.responses;

import com.thesysieq.isa.trainfo.trainfo.data.entity.StopEntity;
import lombok.Builder;
import lombok.Value;

import java.sql.Time;
import java.util.UUID;

@Value
public class StopResponseDto {
    UUID stopId;
    Integer trainNumber;
    String station;
    Time arrival;
    Time departure;
    Float distanceFromOriginKm;

    public StopResponseDto transferToDto(StopEntity stopEntity) {
        return new StopResponseDto(
                stopEntity.getStopID(),
                stopEntity.getTrain().getTrainNumber(),
                stopEntity.getStation(),
                stopEntity.getArrival(),
                stopEntity.getDeparture(),
                stopEntity.getDistanceFromOriginKm()
        );
    }
}
