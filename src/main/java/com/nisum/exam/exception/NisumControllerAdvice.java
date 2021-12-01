package com.nisum.exam.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class NisumControllerAdvice {

    @ExceptionHandler({BadCredentialsException.class, InvalidEmailException.class
    	, MethodArgumentNotValidException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<NisumErrorResponse> handleBadRequestException(Exception ex) {
        log.error("BadRequestException: {}", ex.getMessage());
        String message;
        if(ex instanceof MethodArgumentNotValidException) {
        	List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult()
        			.getFieldErrors();
        	message = fieldErrors.stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(" | "));
        }else {
        	message = "Ocurrió un error general";
        }
        
        return new ResponseEntity<>(new NisumErrorResponse(message)
        		, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<NisumErrorResponse> handleException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(new NisumErrorResponse("Ocurrió un error general")
        		, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
