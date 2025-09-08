package com.farmacia.data.entity;

import com.farmacia.data.dto.FornecedorDTO;
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

    public static FornecedorEntity withFornecedor(FornecedorDTO fornecedor){
        return FornecedorEntity.builder()
                .nome(fornecedor.nome())
                .cnpj(fornecedor.cnpj())
                .endereco(fornecedor.endereco())
                .telefone(fornecedor.telefone())
                .eMail(fornecedor.eMail())
                .build();
    }
}
