package com.thesysieq.isa.trainfo.trainfo.data.entity;

import java.io.Serializable;

import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.StopDto;
import lombok.*;
import java.sql.Time;

@Builder
@Data

public class StopEntity implements Comparable<StopEntity>, Serializable {
    @ToString.Exclude
    //@EqualsAndHashCode.Exclude
    private TrainEntity train;
    private String station;
    private Time arrival;
    private Time departure;
    private Float distanceFromOriginKm;

    @Override
    public int compareTo(StopEntity o) {
        int result = train.getTrainNumber().compareTo(o.getTrain().getTrainNumber());
        if(result == 0){
            result = distanceFromOriginKm.compareTo(o.getDistanceFromOriginKm());
        }
        else result = this.hashCode() - o.hashCode();
        return result;
    }

    public StopDto transferToDto() {
        return StopDto.builder()
                .trainNumber(this.train.getTrainNumber())
                .station(this.station)
                .arrival(this.arrival.toString())
                .departure(this.departure.toString())
                .distanceFromOriginKm(this.distanceFromOriginKm)
                .build();

    }
}
