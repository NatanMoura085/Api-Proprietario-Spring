package com.algaworks.algatransito.api.exceptiohandler;

import com.algaworks.algatransito.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class ApiexceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("campos invalidos");
        problemDetail.setType(URI.create("http://localhost:8080/errors/campo-invalidos"));
        Map<String, String> fields = ex.getBindingResult().getAllErrors().stream().collect(Collectors.toMap(objectError-> ((FieldError) objectError).getField(),
                objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())
                ));
        problemDetail.setProperty("fields",fields);
        return handleExceptionInternal(ex,problemDetail,headers,status,request);
    }

    @ExceptionHandler(NegocioException.class)
    public ProblemDetail negocioHandle(NegocioException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("http://localhost:8081/errors/regra-de-negocio"));
        return problemDetail;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegry(DataIntegrityViolationException e){
        ProblemDetail problemDetail1 = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail1.setTitle("recurso ja em uso");
        problemDetail1.setType(URI.create("http://localhost:8081/erros/recuros-em-uso"));
        return problemDetail1;

    }
}
