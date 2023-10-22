package org.leniv.distributed.systems.user.dto;

import lombok.Data;
import org.leniv.distributed.systems.user.entity.User;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private User.Role role;
}
