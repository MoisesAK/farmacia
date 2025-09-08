package com.farmacia.service;

import com.farmacia.data.dto.PedidoDTO;
import com.farmacia.data.entity.PedidoEntity;
import com.farmacia.exception.BusinessException;
import com.farmacia.repository.PedidosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PedidosService {

    private final PedidosRepository repository;
    private final ProdutoService produtoService;

    public PedidoEntity fazerPedido(PedidoDTO pedidoDTO) {
        produtoService.validaProdutos(pedidoDTO.produto(), pedidoDTO.idFornecedor());
        return repository.save(PedidoEntity.withPedito(pedidoDTO));
    }



    public PedidoEntity buscarPedido(String nroPedido) {
        return repository.findByNroPedido(nroPedido)
                .orElseThrow(() -> new BusinessException("pedido não encontrado"));
    }

    public void confirmarRecebimento(String nroPedido) {
        var pedido = repository.findByNroPedido(nroPedido)
                .filter(p -> !p.recebido())
                .orElseThrow(() -> new BusinessException("pedido não encontrado ou já recebido"));

        produtoService.validaProdutos(pedido.produto(), pedido.nroFornecedor());

        pedido.produto().forEach(produtoService::salvaProduto);
        repository.save(pedido.toBuilder().recebido(Boolean.TRUE).build());

    }
}
