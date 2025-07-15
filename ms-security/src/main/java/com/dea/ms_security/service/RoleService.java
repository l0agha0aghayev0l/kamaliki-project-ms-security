package com.dea.ms_security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dea.ms_security.entity.Role;
import com.dea.ms_security.enumeration.UserRoleEnum;
import com.dea.ms_security.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    
    private final RoleRepository roleRepository;


    public List<Role> getRolesByName(UserRoleEnum... roles) {
        List<Role> roleList = new ArrayList<>();
        for (UserRoleEnum ure : roles) {
            Role role = roleRepository.findByName(ure.name());
            if (role != null) {
                roleList.add(role);
            }
        }
        return roleList;
    }
}
