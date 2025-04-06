package com.tagaddod.tests;

import com.tagaddod.models.requests.CreateTripRequest;
import com.tagaddod.utils.GraphQLUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateTripTest extends BaseTest {

    @BeforeMethod
    public void loginSetup() {
        LoginTest loginTest = new LoginTest();
        loginTest.testSuccessfulLogin();
    }

    @DataProvider(name = "tripData")
    public Object[][] getTripData() {
        return new Object[][] {
            {
                CreateTripRequest.builder()
                    .warehouseTypeId(1)
                    .warehouseId(1)
                    .collectorId(410)
                    .collectionDate("2025-04-22 00:00:00")
                    .requestsIds(Arrays.asList())
                    .build()
            }
        };
    }

    @Test(dataProvider = "tripData")
    public void testCreateTrip(CreateTripRequest request) {
        String query = GraphQLUtils.loadGraphQLQuery("createTrip");

        Map<String, Object> variables = Map.of(
            "warehouse_type_id", request.getWarehouseTypeId(),
            "warehouse_id", request.getWarehouseId(),
            "collector_id", request.getCollectorId(),
            "collection_date", request.getCollectionDate(),
            "requests_ids", request.getRequestsIds()
        );

        Response response = graphQLClient.executeGraphQL(query, variables);
        
        assertEquals(response.statusCode(), 200);
        assertNotNull(response.jsonPath().getString("data.createTripTest.id"));
        assertNotNull(response.jsonPath().getString("data.createTripTest.status"));
    }
}