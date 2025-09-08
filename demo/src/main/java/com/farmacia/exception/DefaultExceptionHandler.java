package com.farmacia.exception;


import com.farmacia.data.dto.ErroDTO;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroDTO> defaultExceptionHandler(BusinessException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ErroDTO.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroDTO> defaultNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErroDTO.builder()
                        .message(exception.getMessage())
                        .build());
    }

}
