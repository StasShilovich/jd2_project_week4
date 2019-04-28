package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.datamodule.model.PermissionEnum;
import com.gmail.shilovich.stas.jd2.datamodule.model.Role;
import com.gmail.shilovich.stas.jd2.datamodule.model.User;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.UserConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole().getPermission().name());
        return userDTO;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        Role role = new Role();
        role.setPermission(PermissionEnum.valueOf(userDTO.getRole()));
        user.setRole(role);
        return user;
    }
}
