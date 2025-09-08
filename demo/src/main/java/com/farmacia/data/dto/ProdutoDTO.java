package com.farmacia.data.dto;

import lombok.Builder;

import java.math.BigDecimal;


@Builder(toBuilder = true)
public record ProdutoDTO(
        String codigo,
        String nome,
        String tipo,
        BigDecimal preco,
        String idFornecedor
) {
}
