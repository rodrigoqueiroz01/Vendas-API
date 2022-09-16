package com.dev.vendas.api.exception.handler;

import com.dev.vendas.api.exception.DataAlreadyRegisteredException;
import com.dev.vendas.api.exception.VendedorNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INVALIDATION_MESSAGE = "Um ou mais campos estão inválidos! " +
            "Faça o preenchimento correto e tente novamente.";

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<ApiError.Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new ApiError.Field(name, message));
        }

        var apiError = new ApiError();
        apiError.setStatus(status.value());
        apiError.setDateTime(OffsetDateTime.now());
        apiError.setTitle(INVALIDATION_MESSAGE);
        apiError.setFields(fields);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(DataAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleDataAlreadyRegisteredException(DataAlreadyRegisteredException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        var apiError = new ApiError();
        apiError.setStatus(status.value());
        apiError.setDateTime(OffsetDateTime.now());
        apiError.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(VendedorNotFoundException.class)
    public ResponseEntity<Object> handleVendedorNotFoundException(VendedorNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        var apiError = new ApiError();
        apiError.setStatus(status.value());
        apiError.setDateTime(OffsetDateTime.now());
        apiError.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Object> handleInternalServerError(HttpServerErrorException.InternalServerError ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        var apiError = new ApiError();
        apiError.setStatus(status.value());
        apiError.setDateTime(OffsetDateTime.now());
        apiError.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

}
