package dev.tulani.anagram;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class AnagramNotFoundException extends RuntimeException {
    public AnagramNotFoundException(String message) {
        super(message);
    }
}
