package com.br.avaliationsystemecommerce.port;
import com.br.avaliationsystemecommerce.domain.Avaliation;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {

    List<Avaliation> findByProductId(Long productId, Pageable pageable);

}
