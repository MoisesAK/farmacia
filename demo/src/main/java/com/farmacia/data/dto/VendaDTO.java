package com.farmacia.data.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record VendaDTO(
        String cpfCliente,
        String nomeVendedor,
        double imposto,
        double margem,
        BigDecimal custos,
        List<Produto> produtos
) {
}
