package org.zenika.zba.zbadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullBodyException extends Exception {
    public  NullBodyException(String message) {
        super(message);
    }
}
