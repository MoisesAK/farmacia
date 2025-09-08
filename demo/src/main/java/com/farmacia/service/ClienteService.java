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

        return repository.save(ClientEntity.builder()
                        .cpf(clienteDTO.cpf())
                        .nome(clienteDTO.nome())
                        .telefone(clienteDTO.telefone())
                        .endereco(clienteDTO.endereco())
                .build());
    }

    public ClientEntity buscaCliente(String cpf) {

        return repository.findClientByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }
}
