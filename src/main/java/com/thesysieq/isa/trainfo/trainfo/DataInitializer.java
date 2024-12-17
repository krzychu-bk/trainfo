package com.thesysieq.isa.trainfo.trainfo;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.StopEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import com.thesysieq.isa.trainfo.trainfo.domain.model.CategoryType;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.CategoryService;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.StopService;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.TrainService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.UUID;

@Component
public class DataInitializer {
    private final CategoryService categoryService;
    private final TrainService trainService;
    private final StopService stopService;

    @Autowired
    public DataInitializer(CategoryService categoryService, TrainService trainService, StopService stopService) {
        this.categoryService = categoryService;
        this.trainService = trainService;
        this.stopService = stopService;
    }

    @PostConstruct
    public void init() {
        //data insertion
        // insert templates:
        //        categoryService.save(category);
        //        trainService.save(train);
        //        timetableEntryService.save(timetableEntry);
        CategoryEntity category = CategoryEntity.builder()
                .categoryId(UUID.randomUUID())
                .categoryType(CategoryType.EIP)
                .businessName("Express Intercity Premium")
                .operatorName("PKP IC")
                .pricePerKmPLN(0.5F)
                .build();
        categoryService.save(category);

        TrainEntity train = TrainEntity.builder()
                .trainId(UUID.randomUUID())
                .category(category)
                .trainNumber(123)
                .build();
        trainService.save(train);

        StopEntity timetableEntry = StopEntity.builder()
                .stopID(UUID.randomUUID())
                .train(train)
                .station("Station A")
                .arrival(new Time(12, 0, 0))
                .departure(new Time(12, 10, 0))
                .distanceFromOriginKm(0F)
                .build();
        stopService.save(timetableEntry);
        System.out.println("[INFO] Data initialized successfully");
    }
}
