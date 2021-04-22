package org.penguin.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.penguin.boot.interceptor.LoggingInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class BootApplication {

    private static ObjectMapper objectMapper;

    private static ConfigurableApplicationContext configurableApplicationContext;

    public static void main(String[] args) {
        configurableApplicationContext = SpringApplication.run(BootApplication.class, args);
        objectMapper = (ObjectMapper) configurableApplicationContext.getBean("jacksonObjectMapper");
        Arrays.stream(configurableApplicationContext.getBeanDefinitionNames()).forEach(name -> {
            log.debug("Terry -Bean> {}", name);
        });
    }

    @Bean
    RestTemplate provideRestTemplate() {
        RestTemplate restTemplate;
        boolean debugEnabled = log.isDebugEnabled();
        if (debugEnabled) {
            ClientHttpRequestFactory factory
                    = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
            restTemplate = new RestTemplate(factory);
        } else {
            restTemplate = new RestTemplate();
        }

        log.debug("Terry -> isDebugEnabled: {}", debugEnabled);
        if (debugEnabled) {
            List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
            if (CollectionUtils.isEmpty(interceptors)) {
                interceptors = new ArrayList<>();
            }
            interceptors.add(new LoggingInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        return restTemplate;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ConfigurableApplicationContext getConfigurableApplicationContext() {
        return configurableApplicationContext;
    }
}
