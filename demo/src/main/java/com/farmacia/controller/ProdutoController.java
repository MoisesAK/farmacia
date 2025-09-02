package com.farmacia.controller;

import com.farmacia.data.dto.ProductDTO;
import com.farmacia.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService service;


    public ProdutoController(ProdutoService service) {
        this.service = service;
    }


    @PostMapping()
    ProductDTO saveProduct(ProductDTO productDTO) {
        return service.saveProduct(productDTO);
    }

    @GetMapping("/{id}")
    ProductDTO findProduct(String id) {
        return service.findProduct(id);
    }

    @PatchMapping("/{id}")
    ProductDTO updateProduct(String id, ProductDTO productDTO) {
        return service.updateProduct(id, productDTO);
    }





}
