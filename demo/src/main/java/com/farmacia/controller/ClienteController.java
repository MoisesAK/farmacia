package com.farmacia.controller;

import com.farmacia.data.dto.ClienteDTO;
import com.farmacia.data.dto.ErroDTO;
import com.farmacia.data.entity.ClientEntity;
import com.farmacia.service.ClienteService;
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
@RequestMapping(value = "/clientes")
public class ClienteController {


    private final ClienteService service;
    @Operation(summary = "Salva cliente", description = "Endpoint responsável por gravar os dados do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário gravado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientEntity.class))),
            @ApiResponse(responseCode = "422", description = "Usuátio já cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))

    })
    @PostMapping()
    ClientEntity gravaCliente(@RequestBody ClienteDTO clienteDTO) {
        return service.gravaCliente(clienteDTO);
    }

    @Operation(summary = "Busca cliente", description = "Endpoint responsável por buscar o cliente pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientEntity.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class)))
    })
    @GetMapping("/{cpf}")
    ClientEntity buscaCliente(@PathVariable String cpf) {
        return service.buscaCliente(cpf);
    }
}
