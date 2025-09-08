package com.farmacia.controller;

import com.farmacia.data.dto.ErroDTO;
import com.farmacia.data.dto.ProdutoDTO;
import com.farmacia.data.entity.ProdutoEntity;
import com.farmacia.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @Operation(summary = "Registra produto", description = "Endpoint responsável por registrar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Registrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoEntity.class))),
            @ApiResponse(responseCode = "422", description = "Ocorreu um erro", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @PostMapping()
    ProdutoEntity registrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return service.registrarProduto(produtoDTO);
    }

    @Operation(summary = "Busca produto", description = "Endpoint responsável por buscar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoEntity.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @GetMapping("/{codigo}")
    ProdutoEntity buscaProduto(@PathVariable String codigo) {
        return service.buscaProduto(codigo);
    }

    @Operation(summary = "Busca produtos", description = "Endpoint responsável por buscar produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoEntity.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @GetMapping()
    List<ProdutoEntity> buscarProdutos(@RequestParam List<String> codigos,
                                       @RequestParam String nome,
                                       @RequestParam String idFornecedor) {
        return service.buscarProdutos(codigos, nome, idFornecedor);
    }

}
