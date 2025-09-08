package com.farmacia.data.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("clientes")
public record ClientEntity(
        String id,
        String cpf,
        String nome,
        String endereco,
        String telefone
) {
}
