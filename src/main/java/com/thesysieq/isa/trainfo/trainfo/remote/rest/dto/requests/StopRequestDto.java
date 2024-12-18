package com.thesysieq.isa.trainfo.trainfo.remote.rest.dto.requests;

import lombok.Value;

import java.sql.Time;

@Value
public class StopRequestDto {
    String station;
    Time arrival;
    Time departure;
    Float distanceFromOriginKm;
}
