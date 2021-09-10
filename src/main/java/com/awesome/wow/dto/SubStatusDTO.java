package com.awesome.wow.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SubStatusDTO {
    List<Integer> payStates;

    List<Integer> logisticsStates;

    List<Integer> pushStates;

    List<Integer> reportStates;
}
