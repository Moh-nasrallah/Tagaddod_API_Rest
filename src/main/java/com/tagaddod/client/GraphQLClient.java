package com.tagaddod.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.tagaddod.config.ConfigManager;
import java.util.Map;

public class GraphQLClient {
    private String authToken;

    public void setAuthToken(String token) {
        this.authToken = token;
    }

    private RequestSpecification getBaseSpec() {
        RequestSpecification spec = RestAssured.given()
            .baseUri(ConfigManager.BASE_URL)
            .contentType(ConfigManager.APPLICATION_JSON);
        
        if (authToken != null) {
            spec.header(ConfigManager.AUTH_TOKEN_HEADER, "Bearer " + authToken);
        }
        
        return spec;
    }

    public Response executeGraphQL(String query, Map<String, Object> variables) {
        return getBaseSpec()
            .body(Map.of(
                "query", query,
                "variables", variables
            ))
            .post();
    }
}