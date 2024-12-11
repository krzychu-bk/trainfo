package com.thesysieq.isa.trainfo.trainfo.remote.rest.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
public class StopDto implements Serializable, Comparable<StopDto> {
    private Integer trainNumber;
    private String station;
    private String arrival;
    private String departure;
    private Float distanceFromOriginKm;

    @Override
    public int compareTo(StopDto o) {
        int result = trainNumber.compareTo(o.trainNumber);
        if (result == 0) {
            result = distanceFromOriginKm.compareTo(o.distanceFromOriginKm);
        }
        if (result == 0) {
            result = this.hashCode() - o.hashCode();
        }
        return result;
    }
}
