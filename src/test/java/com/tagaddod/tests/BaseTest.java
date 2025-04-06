package com.tagaddod.tests;

import com.tagaddod.client.GraphQLClient;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected GraphQLClient graphQLClient;

    @BeforeClass
    public void setup() {
        graphQLClient = new GraphQLClient();
    }
}