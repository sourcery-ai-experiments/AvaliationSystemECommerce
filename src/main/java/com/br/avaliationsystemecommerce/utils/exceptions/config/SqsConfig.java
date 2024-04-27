package com.br.avaliationsystemecommerce.utils.exceptions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create("localstack"))
                .endpointOverride(URI.create("http://127.0.0.1:4566"))
                .region(Region.US_EAST_1)
                .build();
    }
}
