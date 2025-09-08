package com.farmacia.data.entity;

import com.farmacia.data.dto.PedidoDTO;
import com.farmacia.data.dto.Produto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
@Document("pedidos")
public record PedidoEntity(
        @Id
        String nroPedido,
        String nroFornecedor,
        List<Produto> produto,
        boolean recebido
) {
        public static PedidoEntity withPedito(PedidoDTO pedidoDTO) {
                return PedidoEntity.builder()
                        .recebido(Boolean.FALSE)
                        .nroPedido(UUID.randomUUID().toString())
                        .nroFornecedor(pedidoDTO.idFornecedor())
                        .produto(pedidoDTO.produto())
                        .build();
        }
}
