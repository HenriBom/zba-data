package org.zenika.zba.zbadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StepsNotFindException extends Exception {
    public StepsNotFindException(String message) { super(message); }
}
