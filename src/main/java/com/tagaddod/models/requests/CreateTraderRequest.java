package com.tagaddod.models.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTraderRequest {
    private String name;
    private String phone;
    private String countryCode;
    private String traderType;
    private Boolean hasWarehouse;
    private String vehicleId;
    private String latitude;
    private String longitude;
    private String pickupNote;
    private String nationalIdImage;
    private String personalImage;
}