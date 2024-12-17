package com.thesysieq.isa.trainfo.trainfo.data.entity;

import com.thesysieq.isa.trainfo.trainfo.domain.model.CategoryType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Entity
@Table(name="categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryEntity{

    @Id
    @Column(name = "category_id", nullable=false, unique=true, updatable=false)
    private UUID categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", nullable=false)
    private CategoryType categoryType;

    @Column(name = "business_name", nullable=false)
    private String businessName;

    @Column(name = "operator_name", nullable=false)
    private String operatorName;

    @Column(name = "price_per_km_pln", nullable=false)
    private Float pricePerKmPLN;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TrainEntity> trains;


}
