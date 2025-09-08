package com.farmacia.repository;

import com.farmacia.data.entity.ProdutoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutosRepository extends MongoRepository<ProdutoEntity, String> {

    Optional<ProdutoEntity> findByCodigo(String codigo);

    boolean existsByCodigoAndIdFornecedor(String codigo, String idFornecedor);

    @Query("{'codigo': {$in: ?0} }")
    List<ProdutoEntity> findProductsByCodigo(List<String> codigoProdutos);

    @Query("{$or: [ {'codigo': {$in: ?0}}, {'nome': ?1}, {'idFornecedor': ?2} ]}")
    List<ProdutoEntity> findByCodigoOrNameOrIdFornecedor(List<String> codigoProdutos, String nome, String idFornecedor);
}
