package com.tagaddod.tests;

import com.tagaddod.models.requests.CreateTraderRequest;
import com.tagaddod.utils.GraphQLUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateTraderTest extends BaseTest {

    @BeforeMethod
    public void loginSetup() {
        // Perform login and set token
        LoginTest loginTest = new LoginTest();
        loginTest.testSuccessfulLogin();
    }

    @DataProvider(name = "traderData")
    public Object[][] getTraderData() {
        return new Object[][] {
            {
                CreateTraderRequest.builder()
                    .name("Test Trader")
                    .phone("1234567890")
                    .countryCode("EG")
                    .traderType("INDIVIDUAL")
                    .hasWarehouse(false)
                    .vehicleId("1")
                    .latitude("30.0444")
                    .longitude("31.2357")
                    .pickupNote("Test pickup note")
                    .nationalIdImage("base64-encoded-image")
                    .personalImage("base64-encoded-image")
                    .build()
            }
        };
    }

    @Test(dataProvider = "traderData")
    public void testCreateTrader(CreateTraderRequest request) {
        String query = GraphQLUtils.loadGraphQLQuery("createTrader");

        Map<String, Object> variables = Map.of(
        );

        Response response = graphQLClient.executeGraphQL(query, variables);
        
        assertEquals(response.statusCode(), 200);
        assertNotNull(response.jsonPath().getString("data.createTrader.id"));
    }
}