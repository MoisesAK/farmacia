package com.farmacia.data.dto;

import com.farmacia.data.entity.ProdutoEntity;
import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record ProdutoCalculado(
        String codigo,
        Integer quantidade,
        BigDecimal total
) {
    public static ProdutoCalculado withProdutoCalculado(ProdutoEntity entity) {
        return ProdutoCalculado.builder()
                .total(entity.preco())
                .codigo(entity.codigo())
                .quantidade(entity.quantidade())
                .build();
    }
}
