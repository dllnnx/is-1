package se.ifmo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ConstraintsViolationException extends HttpClientErrorException {

    public ConstraintsViolationException(String msg) {
        super(HttpStatus.BAD_REQUEST, msg);
    }
}
