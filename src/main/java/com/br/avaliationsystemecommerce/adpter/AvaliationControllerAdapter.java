package com.br.avaliationsystemecommerce.adpter;


import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.dto.AvaliationAverageOutput;
import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import com.br.avaliationsystemecommerce.port.AvaliationControllerPort;
import com.br.avaliationsystemecommerce.service.AvaliationServicePersistence;
import com.br.avaliationsystemecommerce.service.AvaliationServiceReading;
import io.swagger.v3.oas.models.parameters.QueryParameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/avaliations")
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
    public ResponseEntity<List<Avaliation>> getCommentsProduct(
            @PathVariable Long productId,
            @RequestParam(name = "page", defaultValue = "0", required = false) @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) @Min(1) Integer size
    ) {
        List<Avaliation> comments = avaliationServiceReading.getCommentsProduct(productId, page, size);
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<AvaliationAverageOutput> getAverageProduct(Long productId) {
        AvaliationAverageOutput response = avaliationServiceReading.getAverageProduct(productId);
        return ResponseEntity.ok(response);
    }
}
