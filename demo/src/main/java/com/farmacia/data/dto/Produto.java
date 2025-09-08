package com.farmacia.data.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record Produto(
        String codigo,
        Integer quantidade
) {
}
