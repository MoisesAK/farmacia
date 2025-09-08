package com.farmacia.repository;

import com.farmacia.data.entity.PedidoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidosRepository extends MongoRepository<PedidoEntity, String> {

    Optional<PedidoEntity> findByNroPedido(String nroPedido);
}
