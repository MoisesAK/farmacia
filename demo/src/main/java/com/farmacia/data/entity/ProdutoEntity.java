package com.farmacia.data.entity;

import com.farmacia.data.dto.ProdutoDTO;
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
    public static ProdutoEntity withProduct(ProdutoDTO produtoDTO) {
        return ProdutoEntity.builder()
                .codigo(produtoDTO.codigo())
                .nome(produtoDTO.nome())
                .tipo(produtoDTO.tipo())
                .preco(produtoDTO.preco())
                .idFornecedor(produtoDTO.idFornecedor())
                .build();
    }
}
