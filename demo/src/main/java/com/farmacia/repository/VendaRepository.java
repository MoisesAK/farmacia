package com.farmacia.repository;

import com.farmacia.data.entity.VendaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendaRepository extends MongoRepository<VendaEntity, String> {


    Optional<VendaEntity> findByNroNota(String nroNota);
}
