package com.farmacia.service;

import com.farmacia.data.dto.Produto;
import com.farmacia.data.dto.ProdutoCalculado;
import com.farmacia.data.dto.ProdutoDTO;
import com.farmacia.data.entity.ProdutoEntity;
import com.farmacia.exception.BusinessException;
import com.farmacia.exception.NotFoundException;
import com.farmacia.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutosRepository repository;
    private final FornecedorService fornecedorService;

    public void salvaProduto(Produto produto) {
        var produtoPersistido = repository.findByCodigo(produto.codigo())
                .orElseThrow(() -> new BusinessException("produto não encontrado"));

        var novaQuantidade = Optional.ofNullable(produtoPersistido.quantidade())
                .map(q -> q + produto.quantidade())
                .orElse(produto.quantidade());

        repository.save(produtoPersistido.toBuilder()
                .quantidade(novaQuantidade)
                .build());
    }

    public ProdutoEntity buscaProduto(String codigo) {

        return repository.findByCodigo(codigo)
                .orElseThrow(() -> new NotFoundException("produto não encontrado"));
    }

    public ProdutoEntity registrarProduto(ProdutoDTO produtoDTO) {

        if(!fornecedorService.existsFornecedor(produtoDTO.idFornecedor())) {
            throw new BusinessException("fornecedor não encontrado");
        }

        return repository.save(ProdutoEntity.builder()
                .codigo(produtoDTO.codigo())
                .nome(produtoDTO.nome())
                .tipo(produtoDTO.tipo())
                .preco(produtoDTO.preco())
                .idFornecedor(produtoDTO.idFornecedor())
                .build());
    }

    public void validaProdutos(List<Produto> produtos, String idFornecedor) {
        if (naoExisteProduto(produtos, idFornecedor)) {
            throw new BusinessException("Um dos produtos não existe na base de dados");
        }
    }

    private boolean naoExisteProduto(List<Produto> produtos, String idFornecedor) {
       return produtos.stream()
                .anyMatch(p -> !repository.existsByCodigoAndIdFornecedor(p.codigo(), idFornecedor));
    }

    public List<ProdutoEntity> buscarProdutos(List<String> codigoProdutos) {
        return repository.findProductsByCodigo(codigoProdutos);
    }

    public List<ProdutoEntity> buscarProdutos(List<String> codigoProdutos, String nome, String idFornecedor) {
        return repository.findByCodigoOrNameOrIdFornecedor(codigoProdutos, nome, idFornecedor);
    }

    public void atualizaEstoque(List<ProdutoCalculado> produtosRecalculados) {
        produtosRecalculados.forEach(this::atualizandoQuantidadeDeProduto);
    }

    private void atualizandoQuantidadeDeProduto(ProdutoCalculado p) {
        repository.findByCodigo(p.codigo())
                .map(produtoEntity -> produtoEntity.toBuilder()
                        .quantidade(produtoEntity.quantidade() - p.quantidade())
                        .build())
                .map(repository::save)
                .orElseThrow(() -> new BusinessException("produto não encontrado"));
    }
}
