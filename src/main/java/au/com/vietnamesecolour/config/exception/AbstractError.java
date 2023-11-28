package au.com.vietnamesecolour.config.exception;

import org.springframework.http.HttpStatus;

public interface AbstractError {

    String getMessage();

    int getCode();

    HttpStatus getHttpStatus();
}
