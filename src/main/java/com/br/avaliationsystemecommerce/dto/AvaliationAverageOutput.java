package com.br.avaliationsystemecommerce.dto;

import java.math.BigDecimal;

public record AvaliationAverageOutput (
        BigDecimal average,
        Long productId
) {
}
