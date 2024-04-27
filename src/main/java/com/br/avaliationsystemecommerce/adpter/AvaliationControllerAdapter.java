package com.br.avaliationsystemecommerce.adpter;


import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import com.br.avaliationsystemecommerce.port.AvaliationControllerPort;
import com.br.avaliationsystemecommerce.service.AvaliationServicePersistence;
import com.br.avaliationsystemecommerce.service.AvaliationServiceReading;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/backoffice")
@AllArgsConstructor
public class AvaliationControllerAdapter implements AvaliationControllerPort {

    private AvaliationServicePersistence avaliationServicePersistence;
    private AvaliationServiceReading avaliationServiceReading;

    @Override
    public ResponseEntity<?> createAvaliation(AvaliationRequest avaliationRequest) {
        avaliationServicePersistence.createAvaliation(avaliationRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<List<Avaliation>> getCommentsProduct(Long productId, Pageable pageable) {
        List<Avaliation> comments = avaliationServiceReading.getCommentsProduct(productId, pageable);
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<?> getAverageProduct(Long productId) {
        return null;
    }
}
