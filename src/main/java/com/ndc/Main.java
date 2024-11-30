package com.ndc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndc.engine.QueryProcessor;
import com.ndc.engine.mysql.MySqlQueryProcessor;
import com.ndc.request.QueryRequest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        String json = "{\n" +
                "  \"collection\": \"Album\",\n" +
                "  \"query\": {\n" +
                "    \"fields\": {\n" +
                "      \"artistId\": {\n" +
                "        \"type\": \"column\",\n" +
                "        \"column\": \"ArtistId\",\n" +
                "        \"fields\": null\n" +
                "      },\n" +
                "      \"title\": {\n" +
                "        \"type\": \"column\",\n" +
                "        \"column\": \"Title\",\n" +
                "        \"fields\": null\n" +
                "      }\n" +
                "    },\n" +
                "    \"predicate\": {\n" +
                "      \"type\": \"binary_comparison_operator\",\n" +
                "      \"column\": {\n" +
                "        \"type\": \"column\",\n" +
                "        \"name\": \"ArtistId\",\n" +
                "        \"path\": []\n" +
                "      },\n" +
                "      \"operator\": \"_lte\",\n" +
                "      \"value\": {\n" +
                "        \"type\": \"scalar\",\n" +
                "        \"value\": \"50\"\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"arguments\": {},\n" +
                "  \"collection_relationships\": {}\n" +
                "}\n";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the JSON string into QueryRequest object
            QueryRequest queryRequest = objectMapper.readValue(json, QueryRequest.class);

            // Output the parsed object
            System.out.println(queryRequest.getCollection());
            QueryProcessor queryProcessor = new MySqlQueryProcessor();
            System.out.println(queryProcessor.generateSQL(queryRequest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}