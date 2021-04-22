package org.penguin.boot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static java.net.URLDecoder.decode;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] responseBody, ClientHttpRequestExecution ex) throws IOException {
        log.debug("Request body for {}: {}", decode(request.getURI().toString(), StandardCharsets.UTF_8.displayName()), new String(responseBody, StandardCharsets.UTF_8));
        ClientHttpResponse response = ex.execute(request, responseBody);
        InputStreamReader isr = new InputStreamReader(
                response.getBody(), StandardCharsets.UTF_8);
        String body = new BufferedReader(isr).lines()
                .collect(Collectors.joining("\n"));
        log.debug("Response body: {}", body);
        return response;
    }
}