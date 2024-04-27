package com.br.avaliationsystemecommerce.dto;
import com.br.avaliationsystemecommerce.domain.Avaliation;

import java.util.List;

public record AvaliationCommentsOutput (
        MetaData metaData,
        List<Avaliation> avaliations
){

}
