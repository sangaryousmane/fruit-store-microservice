package com.ousmane.authenticationservice.exceptions.error;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ApiExceptionHandler implements Serializable {

    private String message;
    private HttpStatus httpStatus;
    private List<String> errorDetails;
    private LocalDateTime timestamp;
    private int statusCode;
}
