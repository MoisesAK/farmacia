package com.farmacia.data.entity;

import lombok.Builder;

@Builder
public record ClientEntity(
        Long id,
        String cpf,
        String nome,
        String endereco,
        String telefone
) {
}
