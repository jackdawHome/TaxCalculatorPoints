package com.homeoffice.points.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringConverter {

    /**
     *  Converts provided data to a specific class type
     * @param data - json string
     * @param type - class type
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T convert(String data, Class<T> type) throws JsonProcessingException, IllegalArgumentException {
        ObjectMapper objectMapper = new ObjectMapper();
        T result = objectMapper.readValue(data, type);
        return result;
    }
}
