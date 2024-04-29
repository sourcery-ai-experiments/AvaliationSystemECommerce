package com.br.avaliationsystemecommerce.service;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SqsProducer {

    private SqsTemplate sqsTemplate;
    private static final String QUEUE_URL = System.getenv("QUEUE_URL");

    public void postMessage(String message) {
        try{
            log.info("Initiating message posting to queue");
            sqsTemplate.send(QUEUE_URL, message);
            log.info("Message posted to queue successfully");
        } catch (Exception e) {
            log.error("Error posting message to queue");
        }
    }

}
