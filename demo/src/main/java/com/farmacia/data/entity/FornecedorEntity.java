package com.farmacia.data.entity;

import lombok.Builder;

@Builder
public record FornecedorEntity(
        Long id,
        String cpf,
        String nome,
        String endereco,
        String telefone
) {
}
