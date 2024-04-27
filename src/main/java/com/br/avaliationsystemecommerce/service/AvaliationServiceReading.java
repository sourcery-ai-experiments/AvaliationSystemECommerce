package com.br.avaliationsystemecommerce.service;

import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.dto.AvaliationAverageOutput;
import com.br.avaliationsystemecommerce.dto.AvaliationCommentsOutput;
import com.br.avaliationsystemecommerce.dto.MetaData;
import com.br.avaliationsystemecommerce.port.AvaliationRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class AvaliationServiceReading {

    private AvaliationRepository avaliationRepository;

    @Cacheable(value = "comments_product", key = "#productId + 0")
    public AvaliationCommentsOutput getCommentsProduct(Long productId, Integer page) {
        Pageable pageable = Pageable.ofSize(10).withPage(page);
        List<Avaliation> comments = avaliationRepository.findByProductId(productId, pageable);
        return new AvaliationCommentsOutput(
                new MetaData(page, 10),
                comments
        );
    }

    @Cacheable(value = "average_product", key = "#productId")
    public AvaliationAverageOutput getAverageProduct(Long productId) {
        BigDecimal average = avaliationRepository.getAverageProduct(productId);
        if (average == null) {
            return new AvaliationAverageOutput(BigDecimal.ZERO, productId);
        }

        return new AvaliationAverageOutput(average.setScale(2, RoundingMode.HALF_UP), productId);
    }
}
