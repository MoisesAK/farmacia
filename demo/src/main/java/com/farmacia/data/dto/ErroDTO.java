package com.farmacia.data.dto;

import lombok.Builder;

@Builder
public record ErroDTO(
        String message
) {
}
