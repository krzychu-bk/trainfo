package com.thesysieq.isa.trainfo.trainfo.remote.rest.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
public class TrainDto implements Comparable<TrainDto>, Serializable {
    private String category;
    private Integer trainNumber;
    private Integer timetableId;

    @Override
    public int compareTo(TrainDto o) {
        int result = this.trainNumber.compareTo(o.trainNumber);
        if (result == 0) {
            result = this.hashCode() - o.hashCode();
        }
        return result;
    }
}
