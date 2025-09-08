package com.farmacia.controller;

import com.farmacia.data.dto.ErroDTO;
import com.farmacia.data.dto.FornecedorDTO;
import com.farmacia.data.entity.FornecedorEntity;
import com.farmacia.service.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping(value = "/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    @Operation(summary = "Salva fornecedor", description = "Endpoint responsável por cadastrar fornecedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FornecedorEntity.class))),
            @ApiResponse(responseCode = "422", description = "Fornecedor já cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @PostMapping()
    FornecedorEntity salvarFornecedor(FornecedorDTO fornecedorDTO) {
        return service.saveFornecedor(fornecedorDTO);
    }

    @Operation(summary = "Busca fornecedor", description = "Endpoint responsável por buscar fornecedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FornecedorEntity.class))),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @GetMapping("/{cnpj}")
    FornecedorEntity buscarBornecedor(@PathVariable String cnpj) {
        return service.buscarFornecedor(cnpj);
    }

}
