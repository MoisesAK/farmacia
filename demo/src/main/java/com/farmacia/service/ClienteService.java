package com.farmacia.service;

import com.farmacia.data.dto.ClienteDTO;
import com.farmacia.data.entity.ClientEntity;
import com.farmacia.exception.BusinessException;
import com.farmacia.exception.NotFoundException;
import com.farmacia.repository.ClientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientesRepository repository;

    public ClientEntity gravaCliente(ClienteDTO clienteDTO) {
        if (repository.existsByCpf(clienteDTO.cpf())){
            throw new BusinessException("Cliente já cadastrado");
        }

        return repository.save(ClientEntity.withCliente(clienteDTO));
    }

    public ClientEntity buscaCliente(String cpf) {

        return repository.findClientByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }
}
