package com.br.avaliationsystemecommerce.service;

import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.dto.AvaliationAverageOutput;
import com.br.avaliationsystemecommerce.dto.AvaliationCommentsOutput;
import com.br.avaliationsystemecommerce.dto.MetaData;
import com.br.avaliationsystemecommerce.port.AvaliationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AvaliationServiceReading {

    private AvaliationRepository avaliationRepository;

    @Cacheable(value = "comments_product", key = "#productId + 0")
    public AvaliationCommentsOutput getCommentsProduct(Long productId, Integer page) {
        log.info("Getting comments for product {}, Page {}", productId, page);
        Pageable pageable = Pageable.ofSize(10).withPage(page);
        try{
            List<Avaliation> comments = avaliationRepository.findByProductId(productId, pageable);
            return new AvaliationCommentsOutput(
                    new MetaData(page, 10),
                    comments
            );
        } catch (Exception e) {
            log.error("Error getting comments for product {}", productId);
            throw  new RuntimeException("Error getting comments for product " + productId, e);
        }
    }

    @Cacheable(value = "average_product", key = "#productId")
    public AvaliationAverageOutput getAverageProduct(Long productId) {
        try{
            BigDecimal average = avaliationRepository.getAverageProduct(productId);
            if (average == null) {
                log.info("No avaliations for product {}", productId);
                return new AvaliationAverageOutput(BigDecimal.ZERO, productId);
            }
            return new AvaliationAverageOutput(average.setScale(2, RoundingMode.HALF_UP), productId);
        } catch (Exception e) {
            log.error("Error getting average for product {}", productId);
            throw  new RuntimeException("Error getting average for product " + productId, e);
        }
    }
}
