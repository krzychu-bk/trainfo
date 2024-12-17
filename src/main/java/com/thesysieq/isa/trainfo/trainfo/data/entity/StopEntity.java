package com.thesysieq.isa.trainfo.trainfo.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.UUID;

@Transactional
@Entity
@Table(name="stops")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class StopEntity {

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
}
