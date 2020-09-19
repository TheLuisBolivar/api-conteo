package com.conteo.api.controllers;

import com.conteo.api.models.dtos.GenericRs;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Log4j2
@ControllerAdvice
public class GlobalControllerException {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptionGeneric(Exception ex, WebRequest request) {
        log.error("[handleExceptionGeneric]: Error inesperado", ex);
        GenericRs genericRs = GenericRs.builder()
                .status(false)
                .description("Error con la validación de los datos").build();
        return new ResponseEntity<>(genericRs, HttpStatus.ACCEPTED);
    }
}
