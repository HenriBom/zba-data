package org.zenika.zba.zbadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFindException extends Exception {
    public RecipeNotFindException(String message) {
        super(message);
    }
}
