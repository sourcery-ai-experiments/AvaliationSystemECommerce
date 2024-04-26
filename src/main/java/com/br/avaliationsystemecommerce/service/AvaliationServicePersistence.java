package com.br.avaliationsystemecommerce.service;


import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import com.br.avaliationsystemecommerce.port.AvaliationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.SqsClient;

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
            SqsClient sqsClient = SqsClient.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                    .build();
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(QUEUE_URL)
                    .messageBody(avaliationRequest.toString())
                    .delaySeconds(1800)
                    .build();
            sqsClient.sendMessage(sendMessageRequest);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
