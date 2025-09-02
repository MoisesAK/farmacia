package com.farmacia.controller;

import com.farmacia.data.dto.ClienteDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidosController {



    @PostMapping()
    ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        return service.saveCliente(clienteDTO);
    }

    @GetMapping("/{id}")
    ClienteDTO findCliente(String id) {
        return service.findCliente(id);
    }
}
