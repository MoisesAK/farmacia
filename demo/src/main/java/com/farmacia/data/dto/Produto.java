package com.farmacia.data.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record Produto(
        String codigo,
        Integer quantidade
) {
}
