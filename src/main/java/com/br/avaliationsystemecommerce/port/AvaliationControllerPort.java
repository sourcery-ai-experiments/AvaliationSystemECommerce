package com.br.avaliationsystemecommerce.port;

import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AvaliationControllerPort {

    @PostMapping
    ResponseEntity<?> createAvaliation(@RequestBody @Valid AvaliationRequest avaliationRequest);

}
