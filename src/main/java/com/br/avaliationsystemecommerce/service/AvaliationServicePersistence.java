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

@Slf4j
@Service
@AllArgsConstructor
public class AvaliationServicePersistence {

    private AvaliationRepository avaliationRepository;
    private static final String QUEUE_URL = System.getenv("QUEUE_URL");

    public void createAvaliation(AvaliationRequest avaliationRequest) {
        Avaliation newAvaliation = createEntity(avaliationRequest);
        try {
            avaliationRepository.save(newAvaliation);
        } catch (Exception e) {
            log.warn("Error creating a new avaliation for product {}. Posting message to queue", avaliationRequest.productId());
            postMessageQueue(avaliationRequest);
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

    private void postMessageQueue(AvaliationRequest avaliationRequest) {
        try {
            log.info("Creating a new connection to SQS");
            SqsClient sqsClient = SqsClient.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                    .build();
            log.info("Successfully connected to SQS. Posting message to queue");
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(QUEUE_URL)
                    .messageBody(avaliationRequest.toString())
                    .delaySeconds(1800)
                    .build();
            sqsClient.sendMessage(sendMessageRequest);
            log.info("Message posted to queue successfully");
        }
        catch (Exception e) {
            log.error("Error posting message to queue");
            log.error("Avaliation: {}", avaliationRequest.toString());
            log.error("Error: {}", e.getMessage());
            System.out.println(e.getMessage());
        }

    }

}
