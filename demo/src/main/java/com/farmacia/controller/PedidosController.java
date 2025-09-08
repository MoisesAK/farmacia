package com.farmacia.controller;

import com.farmacia.data.dto.ErroDTO;
import com.farmacia.data.dto.PedidoDTO;
import com.farmacia.data.entity.PedidoEntity;
import com.farmacia.service.PedidosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping(value = "/pedidos")
public class PedidosController {

    private final PedidosService service;


    @Operation(summary = "Grava pedido", description = "Endpoint responsável por cadastrar pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido gravado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoEntity.class))),
            @ApiResponse(responseCode = "422", description = "Ocorreu um erro", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @PostMapping()
    PedidoEntity fazerPedido(@RequestBody PedidoDTO pedidoDTO) {
        return service.fazerPedido(pedidoDTO);
    }

    @Operation(summary = "Busca pedido", description = "Endpoint responsável por buscar pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "pedido encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoEntity.class))),
            @ApiResponse(responseCode = "404", description = "pedido não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @GetMapping("/{nroPedido}")
    PedidoEntity buscarPedido(@PathVariable String nroPedido) {
        return service.buscarPedido(nroPedido);
    }

    @Operation(summary = "Confirma pedido", description = "Endpoint responsável por confirmar recebimento de pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "pedido não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(responseCode = "422", description = "Ocorreu um erro", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @PatchMapping("/{nroPedido}")
    ResponseEntity<Void> confirmarRecebimento(@PathVariable String nroPedido) {
        service.confirmarRecebimento(nroPedido);
        return ResponseEntity.noContent().build();
    }

}
