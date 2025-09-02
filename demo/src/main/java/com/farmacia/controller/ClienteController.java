package com.farmacia.controller;

import com.farmacia.data.dto.ClienteDTO;
import com.farmacia.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {


    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping()
    ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        return service.saveCliente(clienteDTO);
    }

    @GetMapping("/{id}")
    ClienteDTO findCliente(String id) {
        return service.findCliente(id);
    }
}
