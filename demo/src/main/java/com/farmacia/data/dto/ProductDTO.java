package com.farmacia.data.dto;

import lombok.Builder;

import java.math.BigDecimal;


@Builder(toBuilder = true)
public record ProductDTO(
        String codigo,
        String nome,
        String tipo,
        BigDecimal preco,
        String fabricante,
        Integer quantidade
) {
}
