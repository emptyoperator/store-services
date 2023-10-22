package org.leniv.distributed.systems.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DuplicateUsernameException extends ResponseStatusException {
    public DuplicateUsernameException(String username) {
        super(HttpStatus.BAD_REQUEST, "Username '%s' is not unique".formatted(username));
    }
}
