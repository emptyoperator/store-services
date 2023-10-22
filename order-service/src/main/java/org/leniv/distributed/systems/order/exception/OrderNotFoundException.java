package org.leniv.distributed.systems.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderNotFoundException extends ResponseStatusException {
    public OrderNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, "Order with id '%s' not found".formatted(id));
    }
}
