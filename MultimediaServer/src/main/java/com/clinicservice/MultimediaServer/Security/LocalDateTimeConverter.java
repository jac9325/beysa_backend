package com.clinicservice.MultimediaServer.Security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeConverter {
    //private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    
    public static class Serializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(formatter.format(value));
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalDateTime.parse(p.getValueAsString(), formatter);
        }
    }
}
