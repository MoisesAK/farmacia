package com.farmacia.data.entity;

import com.farmacia.data.dto.Produto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder(toBuilder = true)
@Document("pedidos")
public record PedidoEntity(
        @Id
        String nroPedido,
        String nroFornecedor,
        List<Produto> produto,
        boolean recebido
) {
}
