package com.thesysieq.isa.trainfo.trainfo.data.entity;

import java.io.Serializable;

import com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.StopDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="stops")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class StopEntity implements Comparable<StopEntity>, Serializable {

    @Id
    @Column(name = "stop_id", nullable=false, unique=true, updatable=false)
    private UUID stopID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable=false)
    @ToString.Exclude
    //@EqualsAndHashCode.Exclude
    private TrainEntity train;

    @Column(name = "station", nullable = false)
    private String station;

    @Column(name = "arrival", nullable = false)
    private Time arrival;

    @Column(name = "departure", nullable = false)
    private Time departure;

    @Column(name = "distance_from_origin_km", nullable = false)
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
