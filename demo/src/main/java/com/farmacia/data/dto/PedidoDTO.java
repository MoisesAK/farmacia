package com.farmacia.data.dto;

import lombok.Builder;

@Builder
public record PedidoDTO(
        Long id,
        Long nroPedido,
        Long idFornecedor,
        ProductDTO produto
) {
}
