package com.dea.ms_security.mapper;

import com.dea.ms_security.response.UserDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.dea.ms_security.dto.UserDto;
import com.dea.ms_security.entity.User;
import com.dea.ms_security.request.RegistrationRequest;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserDto toUserDto(User user);
    UserDto toUserDto(RegistrationRequest registrationRequest);

    User toUser(UserDto userDto);

    UserDataResponse toUserDataResponse(User user);
}
