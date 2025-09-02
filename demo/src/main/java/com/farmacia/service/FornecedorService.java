package com.farmacia.service;

import com.farmacia.data.dto.FornecedorDTO;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    public FornecedorDTO saveFornecedor(FornecedorDTO fornecedorDTO) {

        return fornecedorDTO;
    }

    public FornecedorDTO findFornecedor(String id) {

        return FornecedorDTO.builder().build();
    }
}
