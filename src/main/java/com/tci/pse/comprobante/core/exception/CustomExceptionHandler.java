package com.tci.pse.comprobante.core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        if (ex.getBindingResult().hasErrors()) {
            List<String> errors = ex.getBindingResult().getFieldErrors()
                    .stream()
                    .map(err -> "El campo '".concat(err.getField()).concat("' ").concat(err.getDefaultMessage()))
                    .collect(Collectors.toList());
            response.put("errors", errors);
        }
        ErrorResponse error = new ErrorResponse("Error en validacion", response);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}