package com.br.avaliationsystemecommerce.utils.exceptions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    private static final String urlAws = System.getenv("URL_AWS");

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create("localstack"))
                .endpointOverride(URI.create(System.getenv(urlAws)))
                .region(Region.US_EAST_1)
                .build();
    }
}
