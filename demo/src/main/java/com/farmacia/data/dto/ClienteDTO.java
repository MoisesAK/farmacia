package com.farmacia.data.dto;

import lombok.Builder;

@Builder
public record ClienteDTO(
        String cpf,
        String nome,
        String endereco,
        String telefone
) {
}
