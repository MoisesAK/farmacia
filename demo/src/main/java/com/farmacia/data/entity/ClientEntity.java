package com.farmacia.data.entity;

import com.farmacia.data.dto.ClienteDTO;
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

    public static ClientEntity withCliente(ClienteDTO cliente){
        return ClientEntity.builder()
                .cpf(cliente.cpf())
                .nome(cliente.nome())
                .telefone(cliente.telefone())
                .endereco(cliente.endereco())
                .build();
    }
}
