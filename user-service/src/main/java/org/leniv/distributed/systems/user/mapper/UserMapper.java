package org.leniv.distributed.systems.user.mapper;

import org.leniv.distributed.systems.user.dto.UserDto;
import org.leniv.distributed.systems.user.dto.UserRegistrationDto;
import org.leniv.distributed.systems.user.dto.UserResponse;
import org.leniv.distributed.systems.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract UserDto userToUserDto(User user);

    public abstract UserResponse userToUserResponse(User user);

    public abstract List<UserDto> usersToUserDtos(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRegistration.getPassword()))")
    public abstract User userRegistrationDtoToUser(UserRegistrationDto userRegistration);
}
