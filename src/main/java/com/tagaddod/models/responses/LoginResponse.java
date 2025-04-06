package com.tagaddod.models.responses;

import lombok.Data;
import java.util.List;

@Data
public class LoginResponse {
    private String id;
    private String phone;
    private List<Role> roles;
    private String jwtToken;

    @Data
    public static class Role {
        private String id;
        private String name;
    }
}