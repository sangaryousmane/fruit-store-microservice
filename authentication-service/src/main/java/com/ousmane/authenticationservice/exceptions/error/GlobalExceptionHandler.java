package com.ousmane.authenticationservice.exceptions.error;


import com.ousmane.authenticationservice.exceptions.RefreshTokenException;
import com.ousmane.authenticationservice.exceptions.RoleNotFoundException;
import com.ousmane.authenticationservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> details = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(media -> builder.append(media).append(", "));

        details.add(builder.toString());
        ApiExceptionHandler apiHandler = ApiExceptionHandler
                .builder().errorDetails(details)
                .message("Invalid JSON").httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(apiHandler);
    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ApiExceptionHandler apiHandler = ApiExceptionHandler
                .builder().errorDetails(getException(ex))
                .message("Malformed JSON request").httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(apiHandler);
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getObjectName() + " : " + err.getDefaultMessage())
                .toList();
        ApiExceptionHandler apiHandler = ApiExceptionHandler
                .builder().errorDetails(details)
                .message("Validation Errors").httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(apiHandler);
    }

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        List<String> details=new ArrayList<>();
        details.add(ex.getParameterName() + "parameter is missing");
        ApiExceptionHandler apiHandler = ApiExceptionHandler
                .builder().errorDetails(details)
                .message("Missing Parameters").httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(apiHandler);
    }


    private List<String> getException(RuntimeException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        return details;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {



        ApiExceptionHandler apiHandler = ApiExceptionHandler
                .builder().errorDetails(getException(ex))
                .message("User not found").httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiHandler);
    }

    // handleRoleException : triggers when there is no resource with the specified ID in RefreshToken
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleException(RoleNotFoundException ex) {

        ApiExceptionHandler apiHandler = ApiExceptionHandler
                .builder().errorDetails(getException(ex))
                .message("User role not found").httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiHandler);
    }

    // handleRefreshTokenException : triggers when there is no resource with the specified ID in RefreshToken
    @ExceptionHandler(RefreshTokenException.class)
    public ResponseEntity<Object> handleRefreshTokenException(
            RefreshTokenException ex) {
        ApiExceptionHandler apiHandler = ApiExceptionHandler
                .builder().errorDetails(getException(ex))
                .message("Refresh Token Not Found").httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiHandler);
    }
}
