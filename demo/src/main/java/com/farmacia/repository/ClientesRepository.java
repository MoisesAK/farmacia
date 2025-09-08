package com.farmacia.repository;

import com.farmacia.data.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends MongoRepository<ClientEntity, String> {

    Optional<ClientEntity> findClientByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
