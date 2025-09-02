package com.farmacia.service;

import com.farmacia.data.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public ProductDTO saveProduct(ProductDTO productDTO) {

        return productDTO;
    }

    public ProductDTO findProduct(String id) {

        return ProductDTO.builder().build();
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        return ProductDTO.builder().build();
    }
}
