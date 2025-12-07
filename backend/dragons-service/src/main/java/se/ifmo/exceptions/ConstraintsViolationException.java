package se.ifmo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConstraintsViolationException extends ResponseStatusException {

    public ConstraintsViolationException(String msg) {
        super(HttpStatus.BAD_REQUEST, msg);
    }
}
