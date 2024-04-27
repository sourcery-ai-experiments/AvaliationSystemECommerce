package com.br.avaliationsystemecommerce.port;
import com.br.avaliationsystemecommerce.domain.Avaliation;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {

    List<Avaliation> findByProductId(Long productId, Pageable pageable);

    @Query(value = "SELECT AVG(a.avaliation) FROM Avaliation a WHERE a.product_id = :productId LIMIT 1000", nativeQuery = true)
    BigDecimal getAverageProduct(Long productId);
}
