package com.farmacia.data.entity;

import com.farmacia.data.dto.ProdutoCalculado;
import com.farmacia.data.dto.VendaDTO;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Document("vendas")
public record VendaEntity(
        String id,
        String cpfCliente,
        String nomeVendedor,
        String nroNota,
        BigDecimal valor,
        List<ProdutoCalculado> listaProdutos
) {

    public static VendaEntity buildVendaEntity(BigDecimal total, VendaDTO vendaDTO, List<ProdutoCalculado> produtosCalculados) {
        return VendaEntity.builder()
                .valor(total)
                .nroNota(UUID.randomUUID().toString())
                .cpfCliente(vendaDTO.cpfCliente())
                .nomeVendedor(vendaDTO.nomeVendedor())
                .listaProdutos(produtosCalculados)
                .build();
    }
}
