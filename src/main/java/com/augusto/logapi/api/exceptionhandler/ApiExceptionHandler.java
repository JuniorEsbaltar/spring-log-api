package com.augusto.logapi.api.exceptionhandler;

import com.augusto.logapi.domain.exception.DomainException;
import com.augusto.logapi.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problem.Field> fields = new ArrayList<>();

        ex.getBindingResult().getAllErrors().stream().forEach(objectError -> {
             fields.add(new Problem.Field(((FieldError) objectError).getField(), messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
        });

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setTitle("Has invalid fields");
        problem.setFields(fields);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(DomainException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex,problem, new HttpHeaders(), status, request);
    }
}
