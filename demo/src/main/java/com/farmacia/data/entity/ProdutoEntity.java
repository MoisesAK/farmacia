package com.farmacia.data.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Builder(toBuilder = true)
@Document("produtos")
public record ProdutoEntity(
        String id,
        String codigo,
        String nome,
        String tipo,
        BigDecimal preco,
        String idFornecedor,
        Integer quantidade
) {
}
