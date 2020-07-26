package com.barrett.zuulgateway;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: smartsite-master
 * @description: The implementation of Hystrix circuit breaker mechanism
 * We should config hystrix in application.properties and implement this class
 * @author: Barrett
 * @create: 2020-05-15 16:03
 **/
@Component
public class ServiceFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        //设置熔断的服务名
        //如果是所有服务则设置为*
        return "ai-alert-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                String returnString = "{'code':000,'message': server error, circuit breaker hystrix in effect}";
                return new ByteArrayInputStream(returnString.getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "{code:404,message:service error =_=}";
            }

            @Override
            public void close() {

            }
        };
    }
}
