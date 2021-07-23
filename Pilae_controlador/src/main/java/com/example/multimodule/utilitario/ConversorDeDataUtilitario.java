package com.example.multimodule.utilitario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversorDeDataUtilitario {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConversorDeDataUtilitario.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public ConversorDeDataUtilitario(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String dtoToJson(final T dto) throws Exception {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error(jsonProcessingException.getMessage());
            throw new Exception(jsonProcessingException.getMessage());
        }
    }
}
