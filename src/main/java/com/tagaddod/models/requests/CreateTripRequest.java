package com.tagaddod.models.requests;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CreateTripRequest {
    private Integer warehouseTypeId;
    private Integer warehouseId;
    private Integer collectorId;
    private String collectionDate;
    private List<Integer> requestsIds;
}