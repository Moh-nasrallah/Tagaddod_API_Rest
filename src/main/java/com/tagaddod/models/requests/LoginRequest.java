package com.tagaddod.models.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String type;
    private String phone;
    private String password;
}