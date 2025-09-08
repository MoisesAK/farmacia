package com.farmacia.service;

import com.farmacia.data.dto.Produto;
import com.farmacia.data.dto.ProdutoCalculado;
import com.farmacia.data.dto.VendaDTO;
import com.farmacia.data.entity.ProdutoEntity;
import com.farmacia.data.entity.VendaEntity;
import com.farmacia.exception.BusinessException;
import com.farmacia.exception.NotFoundException;
import com.farmacia.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository repository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;

    public VendaEntity processar(VendaDTO vendaDTO) {

        clienteService.buscaCliente(vendaDTO.cpfCliente());

        var produtos = produtoService.buscarProdutos(listaDeCodigosDeProduto(vendaDTO));

        validaQuantidadeProdutos(vendaDTO, produtos);

        var produtosRecalculados = getProdutosRecalculados(vendaDTO, produtos, getImpostoEMargem(vendaDTO));

        var total = produtosRecalculados.stream()
                .map(ProdutoCalculado::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        produtoService.atualizaEstoque(produtosRecalculados);


        return repository.save(VendaEntity.buildVendaEntity(total, vendaDTO, produtosRecalculados));
    }

    private static Double getImpostoEMargem(VendaDTO vendaDTO) {
        return Optional.of(vendaDTO.margem())
                .map(m -> 1 + m + vendaDTO.imposto())
                .orElse(1 + vendaDTO.imposto());
    }

    private static List<String> listaDeCodigosDeProduto(VendaDTO vendaDTO) {
        return vendaDTO.produtos()
                .stream()
                .map(Produto::codigo)
                .toList();
    }

    private static void validaQuantidadeProdutos(VendaDTO vendaDTO, List<ProdutoEntity> produtos) {
        if (CollectionUtils.isEmpty(produtos) || vendaDTO.produtos().size() != produtos.size()){
            throw new BusinessException("não existem produtos cadastrados com todos os codigos");
        }

        if (naoExistemProdutosSuficientes(vendaDTO, produtos)){
            throw new BusinessException("não existem produtos suficientes");
        }
    }

    private static boolean naoExistemProdutosSuficientes(VendaDTO vendaDTO, List<ProdutoEntity> produtos) {
        return produtos.stream()
                .anyMatch(p -> quantidadeASerComprada(vendaDTO, p) > p.quantidade());
    }

    private static Integer quantidadeASerComprada(VendaDTO vendaDTO, ProdutoEntity p) {
        return vendaDTO.produtos().stream()
                .filter(vp -> vp.codigo().equals(p.codigo()))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Produto da lista não consta no estoque"))
                .quantidade();
    }

    private static List<ProdutoCalculado> getProdutosRecalculados(VendaDTO vendaDTO, List<ProdutoEntity> produtos, Double impostoEMargem) {

        return produtos.stream()
                .filter(Objects::nonNull)
                .map(p -> p.toBuilder()
                        .preco(getFinalPreco(vendaDTO, impostoEMargem, p))
                        .quantidade(findQuantidade(vendaDTO, p))
                        .build())
                .map(ProdutoCalculado::withProdutoCalculado)
                .toList();
    }



    private static BigDecimal getFinalPreco(VendaDTO vendaDTO, Double impostoEMargem, ProdutoEntity produtoEntity) {
        return produtoEntity.preco()
                .multiply(BigDecimal.valueOf(impostoEMargem))
                .add(vendaDTO.custos())
                .multiply(BigDecimal.valueOf(findQuantidade(vendaDTO, produtoEntity)));
    }

    private static Integer findQuantidade(VendaDTO vendaDTO, ProdutoEntity p) {
        return vendaDTO.produtos().stream()
                .filter(pr -> pr.codigo().equals(p.codigo()))
                .map(Produto::quantidade)
                .findFirst()
                .orElseThrow(() -> new BusinessException("Produtos da lista não estão registrados"));
    }

    public VendaEntity buscarVenda(String nroNota) {
        return repository.findByNroNota(nroNota)
                .orElseThrow(() -> new NotFoundException("Venda não encontrada"));
    }


    public List<VendaEntity> relatorioDeVendas(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findAll();
    }
}
