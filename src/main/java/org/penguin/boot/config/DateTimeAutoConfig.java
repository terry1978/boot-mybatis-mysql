package org.penguin.boot.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class DateTimeAutoConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer
    jacksonObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            final String dateFormat = "yyyy-MM-dd";
            final String timeFormat = "HH:mm:ss";
            final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss"; // hh:mm:ss a -> 12小时,显示上下午
            jacksonObjectMapperBuilder
                    .serializers(
                            new LocalDateSerializer(
                                    DateTimeFormatter.ofPattern(dateFormat)))
                    .deserializers(
                            new LocalDateDeserializer(
                                    DateTimeFormatter.ofPattern(dateFormat)))
                    .serializers(
                            new LocalTimeSerializer(
                                    DateTimeFormatter.ofPattern(timeFormat)))
                    .deserializers(
                            new LocalTimeDeserializer(
                                    DateTimeFormatter.ofPattern(timeFormat)))
                    .serializers(
                            new LocalDateTimeSerializer(
                                    DateTimeFormatter.ofPattern(dateTimeFormat)))
                    .deserializers(
                            new LocalDateTimeDeserializer(
                                    DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }
}
