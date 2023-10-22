package org.leniv.distributed.systems.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundException extends ResponseStatusException {
    public ProductNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, "Product with id '%s' not found".formatted(id));
    }
}
