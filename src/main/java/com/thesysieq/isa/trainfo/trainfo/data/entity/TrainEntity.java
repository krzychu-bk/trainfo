package com.thesysieq.isa.trainfo.trainfo.data.entity;

import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.TrainDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Builder
@Data
public class TrainEntity implements Comparable<TrainEntity>, Serializable {

    @Id
    @Column(name = "train_id", unique = true, nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CategoryEntity category;

    @Column(name = "train_number", nullable = false)
    private Integer trainNumber;

    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
