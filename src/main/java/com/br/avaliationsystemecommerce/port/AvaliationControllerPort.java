package com.br.avaliationsystemecommerce.port;

import com.br.avaliationsystemecommerce.dto.AvaliationAverageOutput;
import com.br.avaliationsystemecommerce.dto.AvaliationCommentsOutput;
import com.br.avaliationsystemecommerce.dto.AvaliationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AvaliationControllerPort {

    @PostMapping
    ResponseEntity<?> createAvaliation(@RequestBody @Valid AvaliationRequest avaliationRequest);

    @GetMapping("/{productId}")
    ResponseEntity<AvaliationCommentsOutput> getCommentsProduct(@PathVariable Long productId,
                                                                @RequestParam(name = "page", defaultValue = "0", required = false) @Min(0) Integer page);

    @GetMapping("/average/{productId}")
    ResponseEntity<AvaliationAverageOutput> getAverageProduct(@PathVariable Long productId);

}
