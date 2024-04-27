package com.br.avaliationsystemecommerce.service;


import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import com.br.avaliationsystemecommerce.port.AvaliationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Slf4j
@Service
@AllArgsConstructor
public class AvaliationServicePersistence {

    private AvaliationRepository avaliationRepository;
    private SqsProducer sqsProducer;

    public void createAvaliation(AvaliationRequest avaliationRequest) {
        Avaliation newAvaliation = createEntity(avaliationRequest);
        try {
            avaliationRepository.save(newAvaliation);
        } catch (Exception e) {
            log.warn("Error creating a new avaliation for product {}. Posting message to queue", avaliationRequest.productId());
            sqsProducer.postMessage(avaliationRequest.toString());
        }
    }

    private Avaliation createEntity(AvaliationRequest avaliationRequest) {
        Avaliation newAvaliation = new Avaliation();
        newAvaliation.setProductId(avaliationRequest.productId());
        newAvaliation.setClientName(avaliationRequest.clientName());
        newAvaliation.setComment(avaliationRequest.comment());
        newAvaliation.setAvaliation(avaliationRequest.avaliation());
        return newAvaliation;
    }

}
