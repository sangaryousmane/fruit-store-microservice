package com.example.fruitsorderservice.external.decoder;

import com.example.fruitsorderservice.exceptions.ExceptionApi;
import com.example.fruitsorderservice.exceptions.OrderNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.ErrorResponse;

import java.io.IOException;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper mapper = new ObjectMapper();
        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ExceptionApi errorResponse=mapper.readValue(
                    response.body().asInputStream(),
                    ExceptionApi.class);
            return new OrderNotFoundException(
                    "order not found", "404");
        }
        catch (IOException ex){
            throw  new OrderNotFoundException(
                    "order not found", "404");
        }
    }
}
