package ru.voronovskii.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request) {
        log.warn("Service Exception {}", exception);
        String errorMessageDescription = exception.getLocalizedMessage();
        if (errorMessageDescription == null) {
            errorMessageDescription = exception.toString();
        }
        return new ResponseEntity<>(new UserErrorMessage(new Date(), "E000",
                errorMessageDescription), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException exception, WebRequest request) {
        log.warn("Business Exception", exception);
        String errorMessageDescription = exception.getLocalizedMessage();
        if (errorMessageDescription == null) {
            errorMessageDescription = exception.toString();
        }
        return new ResponseEntity<>(new UserErrorMessage(new Date(), exception.getErrorCode(),
                errorMessageDescription), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
