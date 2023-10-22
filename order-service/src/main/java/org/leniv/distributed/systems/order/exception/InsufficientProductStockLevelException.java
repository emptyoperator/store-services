package org.leniv.distributed.systems.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientProductStockLevelException extends ResponseStatusException {
    public InsufficientProductStockLevelException(long id) {
        super(HttpStatus.BAD_REQUEST, "Product with id '%s' has insufficient stock level".formatted(id));
    }
}
