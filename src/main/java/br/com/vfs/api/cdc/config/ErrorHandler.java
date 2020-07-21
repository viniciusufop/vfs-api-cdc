package br.com.vfs.api.cdc.config;

import br.com.vfs.api.cdc.shared.errors.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                            final HttpHeaders headers, HttpStatus status, final WebRequest request) {
        var errors = getErrors(ex);
        log.info("M=handleMethodArgumentNotValid, errors:{}", errors);
        var url = ((ServletWebRequest) request).getRequest().getRequestURI();
        var errorMessage = new ErrorMessage(errors, url);
        return super.handleExceptionInternal(ex, errorMessage, headers, status, request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handle(final NotFoundException ex, final WebRequest request) {
        var errors = List.of(ex.getMessage());
        log.info("M=NotFoundException, errors:{}", errors);
        var url = ((ServletWebRequest) request).getRequest().getRequestURI();
        var errorMessage = new ErrorMessage(errors, url);
        return super.handleExceptionInternal(ex, errorMessage, null, NOT_FOUND, request);
    }

    private List<String> getErrors(final MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(this::getFinalMessage)
                .collect(Collectors.toList());
    }

    private String getFinalMessage(final FieldError fieldError) {
        return String.format("field: %s, message: %s",fieldError.getField(), fieldError.getDefaultMessage());
    }
}
