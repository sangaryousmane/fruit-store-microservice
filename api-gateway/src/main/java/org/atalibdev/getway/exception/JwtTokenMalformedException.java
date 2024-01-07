package org.atalibdev.getway.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class JwtTokenMalformedException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public JwtTokenMalformedException(String explanation) {
        super(explanation);
    }

}
