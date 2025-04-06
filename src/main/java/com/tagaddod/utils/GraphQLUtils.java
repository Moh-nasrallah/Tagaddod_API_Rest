package com.tagaddod.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphQLUtils {
    
    public static String loadGraphQLQuery(String fileName) {
        try {
            return new String(Files.readAllBytes(
                Paths.get("src/main/resources/graphql/mutations/" + fileName + ".graphql")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load GraphQL query: " + fileName, e);
        }
    }
}