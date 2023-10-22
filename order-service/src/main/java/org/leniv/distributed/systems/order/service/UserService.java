package org.leniv.distributed.systems.order.service;

import org.leniv.distributed.systems.order.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserService {
    @GetExchange("/username/{username}")
    UserDto findByUsername(@PathVariable String username);
}
