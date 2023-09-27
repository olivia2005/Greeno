package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.SearchData;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {

    public static SearchData readTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("testData.json"), SearchData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file: testData.json", e);
        }
    }
}
