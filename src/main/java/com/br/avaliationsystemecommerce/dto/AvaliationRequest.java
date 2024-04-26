package com.br.avaliationsystemecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AvaliationRequest (
        @NotBlank @Size(max=155) String clientName,
        @Size(max=1500) String comment,
        @Valid() Float avaliation,
        @NotNull Long productId
) {
}
