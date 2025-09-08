package com.farmacia.data.dto;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record PedidoDTO(
        String nroPedido,
        String idFornecedor,
        ArrayList<Produto> produto
){}
