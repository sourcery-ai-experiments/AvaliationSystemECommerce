package com.br.avaliationsystemecommerce.adpter;


import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import com.br.avaliationsystemecommerce.port.AvaliationControllerPort;
import com.br.avaliationsystemecommerce.service.AvaliationServicePersistence;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/backoffice")
@AllArgsConstructor
public class AvaliationControllerAdapter implements AvaliationControllerPort {

    private AvaliationServicePersistence avaliationServicePersistence;

    @Override
    public ResponseEntity<?> createAvaliation(AvaliationRequest avaliationRequest) {
        avaliationServicePersistence.createAvaliation(avaliationRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
