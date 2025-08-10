package com.martin.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDatos implements IConversorDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertirDatos(String rawJson, Class<T> clase) {
        try {
            return objectMapper.readValue(rawJson, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
