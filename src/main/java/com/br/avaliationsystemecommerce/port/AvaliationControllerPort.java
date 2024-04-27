package com.br.avaliationsystemecommerce.port;

import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AvaliationControllerPort {

    @PostMapping
    ResponseEntity<?> createAvaliation(@RequestBody @Valid AvaliationRequest avaliationRequest);

    @GetMapping
    ResponseEntity<?> getCommentsProduct(@RequestParam Long productId, Pageable pageable);

    @GetMapping("/average")
    ResponseEntity<?> getAverageProduct(@RequestParam Long productId);

}
