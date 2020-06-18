package org.zenika.zba.zbadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StepsNotFoundException extends Exception {
    public StepsNotFoundException(String message) { super(message); }
}
