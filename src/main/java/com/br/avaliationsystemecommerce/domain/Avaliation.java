package com.br.avaliationsystemecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Avaliation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;

    private String comment;

    @Column(nullable = false)
    private Float avaliation;

    private Long productId;

}
