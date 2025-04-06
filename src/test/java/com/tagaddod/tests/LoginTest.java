package com.tagaddod.tests;

import com.tagaddod.models.requests.LoginRequest;
import com.tagaddod.utils.GraphQLUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        // Load the login mutation
        String loginQuery = GraphQLUtils.loadGraphQLQuery("login");

        // Prepare login variables
        LoginRequest loginRequest = LoginRequest.builder()
            .type("PHONE")
            .phone("01009295535")
            .password("123123")
            .build();

        Map<String, Object> variables = Map.of(
            "type", loginRequest.getType(),
            "phone", loginRequest.getPhone(),
            "password", loginRequest.getPassword()
        );

        // Execute the login mutation
        Response response = graphQLClient.executeGraphQL(loginQuery, variables);

        // Verify response
        assertEquals(response.statusCode(), 200);
        response.then().log().all(); // Log the full response for debugging


        String token = response.jsonPath().getString("data.login.jwtToken");
        assertNotNull(token, "JWT token should not be null");
        
        // Set the token for subsequent requests
        graphQLClient.setAuthToken(token);
    }
}