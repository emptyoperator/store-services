package org.leniv.distributed.systems.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AdminUserDisablingException extends ResponseStatusException {
    public AdminUserDisablingException(long id) {
        super(HttpStatus.BAD_REQUEST, "Admin user with id '%s' cannot be disabled".formatted(id));
    }
}
