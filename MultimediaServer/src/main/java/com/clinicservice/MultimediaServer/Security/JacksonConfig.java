package com.clinicservice.MultimediaServer.Security;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {

    @Bean(name = "customObjectMapper")
    public ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Configura el formato de fecha
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        objectMapper.setDateFormat(df);

        return objectMapper;
    }
}