package com.farmacia.service;

import com.farmacia.data.dto.FornecedorDTO;
import com.farmacia.data.entity.FornecedorEntity;
import com.farmacia.exception.BusinessException;
import com.farmacia.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorEntity saveFornecedor(FornecedorDTO fornecedorDTO) {

        if(repository.existsByCnpj(fornecedorDTO.cnpj())){
            throw new BusinessException("Fornecedor ja cadastrado");
        }

        return repository.save(FornecedorEntity.builder()
                        .nome(fornecedorDTO.nome())
                        .cnpj(fornecedorDTO.cnpj())
                        .endereco(fornecedorDTO.endereco())
                        .telefone(fornecedorDTO.telefone())
                        .eMail(fornecedorDTO.eMail())
                .build());
    }

    public FornecedorEntity buscarFornecedor(String cnpj) {

        return repository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    public boolean existsFornecedor(String id) {

        return repository.existsById(id);
    }
}
