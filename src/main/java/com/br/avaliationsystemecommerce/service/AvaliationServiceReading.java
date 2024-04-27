package com.br.avaliationsystemecommerce.service;

import com.br.avaliationsystemecommerce.domain.Avaliation;
import com.br.avaliationsystemecommerce.port.AvaliationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvaliationServiceReading {

    private AvaliationRepository avaliationRepository;

    public List<Avaliation> getCommentsProduct(Long productId, Pageable pageable) {
        return avaliationRepository.findByProductId(productId, pageable);
    }
}
