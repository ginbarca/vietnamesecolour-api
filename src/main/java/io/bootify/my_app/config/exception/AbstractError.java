package io.bootify.my_app.config.exception;

import org.springframework.http.HttpStatus;

public interface AbstractError {

    String getMessage();

    int getCode();

    HttpStatus getHttpStatus();
}
