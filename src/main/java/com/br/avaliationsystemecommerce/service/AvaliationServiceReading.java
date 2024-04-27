package com.br.avaliationsystemecommerce.service;

import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.dto.AvaliationAverageOutput;
import com.br.avaliationsystemecommerce.port.AvaliationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class AvaliationServiceReading {

    private AvaliationRepository avaliationRepository;

    public List<Avaliation> getCommentsProduct(Long productId, Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size > 25 ? 25 : size).withPage(page);
        return avaliationRepository.findByProductId(productId, pageable);
    }

    public AvaliationAverageOutput getAverageProduct(Long productId) {
        BigDecimal average = avaliationRepository.getAverageProduct(productId);
        if (average == null) {
            return new AvaliationAverageOutput(BigDecimal.ZERO, productId);
        }

        return new AvaliationAverageOutput(average.setScale(2, RoundingMode.HALF_UP), productId);
    }
}
