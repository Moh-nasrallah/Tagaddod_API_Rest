package com.tagaddod.models.requests;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CreateBranchRequest {
    private String countryCode;
    private String phoneNumber;
    private String businessClientId;
    private String paymentType;
    private String preferredTime;
    private String longitude;
    private String latitude;
    private String addressNotes;
    private Float weeklyProduction;
    private Float storageCapacity;
    private Float price;
    private List<String> preferredDays;
    private List<BranchNumber> branchNumbers;
    private String signImage;

    @Data
    @Builder
    public static class BranchNumber {
        private String number;
        private String type;
    }
}