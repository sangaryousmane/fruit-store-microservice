package com.example.fruitsorderservice.external.decoder;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoderConfig {


    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }
}
