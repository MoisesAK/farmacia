package com.farmacia.data.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("fornecedores")
public record FornecedorEntity(
        String id,
        String nome,
        String cnpj,
        String endereco,
        String telefone,
        String eMail
) {
}
