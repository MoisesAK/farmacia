package com.farmacia.data.dto;

import lombok.Builder;

@Builder
public record FornecedorDTO(
       Long id,
       String nome,
       String CNPJ,
       String endereço,
       String telefone,
       String eMail
) {
}
