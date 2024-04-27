package com.br.avaliationsystemecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AvaliationRequest (
        @NotBlank @Size(max=155) String clientName,
        @Size(max=1500) String comment,
        @Valid() BigDecimal avaliation,
        @NotNull Long productId
) {
}
