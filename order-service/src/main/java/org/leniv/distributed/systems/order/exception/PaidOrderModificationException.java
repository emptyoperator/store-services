package org.leniv.distributed.systems.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaidOrderModificationException extends ResponseStatusException {
    public PaidOrderModificationException(long id) {
        super(HttpStatus.BAD_REQUEST, "Paid order with id '%s' cannot be modified".formatted(id));
    }
}
