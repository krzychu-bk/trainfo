package com.thesysieq.isa.trainfo.trainfo.data.entity;

import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.TrainDto;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class TrainEntity implements Comparable<TrainEntity>, Serializable {
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CategoryEntity category;
    private Integer trainNumber;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<StopEntity> timetable;

    @Override
    public int compareTo(TrainEntity o) {
        int result = this.trainNumber.compareTo(o.getTrainNumber());
        if(result==0){
            result = this.hashCode() - o.hashCode();
        }
        return result;
    }

    public TrainDto transferToDto(){
        return TrainDto.builder()
                .category(this.category.getBusinessName())
                .trainNumber(this.trainNumber)
                .timetableId(this.timetable.get(0).getTrain().getTrainNumber())
                .build();
    }
}
