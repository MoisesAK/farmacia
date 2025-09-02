package com.farmacia.controller;

import com.farmacia.data.dto.FornecedorDTO;
import com.farmacia.service.FornecedorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fornecedores")

public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping()
    FornecedorDTO saveFornecedor(FornecedorDTO fornecedorDTO) {
        return service.saveFornecedor(fornecedorDTO);
    }

    @GetMapping("/{id}")
    FornecedorDTO findProduct(String id) {
        return service.findFornecedor(id);
    }

}
