package com.farmacia.controller;

import com.farmacia.data.dto.ErroDTO;
import com.farmacia.data.dto.VendaDTO;
import com.farmacia.data.entity.PedidoEntity;
import com.farmacia.data.entity.VendaEntity;
import com.farmacia.service.VendaService;
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
@RequestMapping(value = "/vendas")
public class VendasController {

    private final VendaService service;

    @Operation(summary = "Registrar venda", description = "Endpoint responsável por registrar venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda Registrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaEntity.class))),
            @ApiResponse(responseCode = "422", description = "Ocorreu um erro", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @PostMapping()
    public VendaEntity processarVenda(@RequestBody VendaDTO vendaDTO) {
        return service.processar(vendaDTO);
    }

    @Operation(summary = "Busca venda", description = "Endpoint responsável por buscar venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaEntity.class))),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @GetMapping("/{nroNota}")
    public VendaEntity buscarVenda(@PathVariable String nroNota) {
        return service.buscarVenda(nroNota);
    }
}
