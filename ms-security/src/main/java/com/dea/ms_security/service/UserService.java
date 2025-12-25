package com.dea.ms_security.service;

import com.dea.ms_security.dto.UserDto;
import com.dea.ms_security.entity.User;
import com.dea.ms_security.enumeration.UserRoleEnum;
import com.dea.ms_security.error.UserIsAlreadyExistsException;
import com.dea.ms_security.mapper.UserMapper;
import com.dea.ms_security.repository.UserRepository;
import com.dea.ms_security.request.RegistrationRequest;
import com.dea.ms_security.response.UserDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public void registration(RegistrationRequest registrationRequest, UserRoleEnum... roles) {
        if (isUserNotExists(registrationRequest.username())) {
            UserDto userDto = userMapper.toUserDto(registrationRequest);
            User user = userMapper.toUser(userDto);
            user.setPassword(passwordEncoder.encode(registrationRequest.password()));
            log.info("ROLES: {}", roleService.getRolesByName(roles));
            user.setRoles(roleService.getRolesByName(roles));
            user.setUuid(UUID.randomUUID().toString());
            log.info("USER CREDENTIALS: {}", user);
            userRepository.save(user);
            return;
        }
        throw new UserIsAlreadyExistsException("User is already exists");
    }

    public UserDataResponse getByUuid(String uuid) {
        var user = userRepository.findByUuid(uuid);
        return userMapper.toUserDataResponse(user);
    }

    private boolean isUserNotExists(String username) {
        return Objects.isNull(userRepository.findByUsername(username));
    }
}
