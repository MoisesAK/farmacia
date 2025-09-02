package com.farmacia.service;

import com.farmacia.data.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {

        return clienteDTO;
    }

    public ClienteDTO findCliente(String id) {

        return ClienteDTO.builder().build();
    }
}
