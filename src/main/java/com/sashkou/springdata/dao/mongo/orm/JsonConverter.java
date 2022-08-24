package com.sashkou.springdata.dao.mongo.orm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sashkou.springdata.exception.ORMException;

import java.util.List;
import java.util.stream.Collectors;

public class JsonConverter<T> {

    private final Class<T> type;
    private final ObjectMapper objectMapper;

    public JsonConverter(Class<T> type) {
        this.type = type;
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<T> fromJson(List<String> jsonObjects) {
        return jsonObjects
                .stream()
                .map(jsonObject -> {
                    try {
                        return objectMapper.readValue(jsonObject, getType());
                    } catch (JsonProcessingException e) {
                        throw new ORMException(e.getMessage());
                    }
                })
                .collect(Collectors.toList());
    }

    public Class<T> getType() {
        return type;
    }
}
