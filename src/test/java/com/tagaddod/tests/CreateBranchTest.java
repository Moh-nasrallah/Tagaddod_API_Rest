package com.tagaddod.tests;

import com.tagaddod.models.requests.CreateBranchRequest;
import com.tagaddod.utils.GraphQLUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateBranchTest extends BaseTest {

    @BeforeMethod
    public void loginSetup() {
        LoginTest loginTest = new LoginTest();
        loginTest.testSuccessfulLogin();
    }

    @DataProvider(name = "branchData")
    public Object[][] getBranchData() {
        return new Object[][] {
            {
                CreateBranchRequest.builder()
                    .countryCode("EG")
                    .phoneNumber("1234567890")
                    .businessClientId("123")
                    .paymentType("CASH")
                    .preferredTime("MORNING")
                    .longitude("31.2357")
                    .latitude("30.0444")
                    .addressNotes("Test address")
                    .weeklyProduction(100.0f)
                    .storageCapacity(1000.0f)
                    .price(50.0f)
                    .preferredDays(Arrays.asList("1", "2", "3"))
                    .branchNumbers(Arrays.asList(
                        CreateBranchRequest.BranchNumber.builder()
                            .number("123456")
                            .type("MOBILE")
                            .build()
                    ))
                    .signImage("base64-encoded-image")
                    .build()
            }
        };
    }

    @Test(dataProvider = "branchData")
    public void testCreateBranch(CreateBranchRequest request) {
        String query = GraphQLUtils.loadGraphQLQuery("createBranch");

        Map<String, Object> variables = Map.of(
        );

        Response response = graphQLClient.executeGraphQL(query, variables);
        
        assertEquals(response.statusCode(), 200);
        assertNotNull(response.jsonPath().getString("data.createBranchB2bForm.id"));
    }
}