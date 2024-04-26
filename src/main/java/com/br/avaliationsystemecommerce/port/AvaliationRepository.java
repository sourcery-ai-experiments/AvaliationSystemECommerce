package com.br.avaliationsystemecommerce.port;
import com.br.avaliationsystemecommerce.domain.Avaliation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {
}
