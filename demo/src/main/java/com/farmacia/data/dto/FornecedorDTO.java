package com.farmacia.data.dto;

import lombok.Builder;

@Builder
public record FornecedorDTO(
       String nome,
       String cnpj,
       String endereco,
       String telefone,
       String eMail
) {
}
