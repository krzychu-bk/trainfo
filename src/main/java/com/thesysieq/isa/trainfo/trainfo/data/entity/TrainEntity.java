package com.thesysieq.isa.trainfo.trainfo.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Entity(name = "trains")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class TrainEntity{

    @ToString.Exclude
    @Id
    @Column(name = "train_id", unique = true, nullable = false, updatable = false)
    private UUID trainId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CategoryEntity category;

    @Column(name = "train_number", nullable = false)
    private Integer trainNumber;

    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<StopEntity> timetable;
}
