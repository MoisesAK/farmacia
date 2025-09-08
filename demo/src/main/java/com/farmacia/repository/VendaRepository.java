package com.farmacia.repository;

import com.farmacia.data.entity.VendaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends MongoRepository<VendaEntity, String> {


    Optional<VendaEntity> findByNroNota(String nroNota);

    @Query("{ 'data': { $gte: ?0, $lte: ?1 } }")
    List<VendaEntity> buscandoVendasEntreDatas(LocalDate dataInicio, LocalDate dataFim);
    //TODO relatorio por tempo
}
