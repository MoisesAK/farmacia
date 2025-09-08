package com.farmacia.repository;

import com.farmacia.data.entity.FornecedorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends MongoRepository<FornecedorEntity, String> {

    Optional<FornecedorEntity> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
}
