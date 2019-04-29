package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.datamodule.model.PermissionEnum;
import com.gmail.shilovich.stas.jd2.datamodule.model.Role;
import com.gmail.shilovich.stas.jd2.datamodule.model.User;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.UserConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.junit.Assert;
import org.junit.Test;

public class UserConverterImplTest {

    @Test
    public void toDTOShouldReturnCorrectData() {
        UserConverter userConverter = new UserConverterImpl();
        User user = new User(
                1l,
                "test",
                "pass",
                new Role(1l, PermissionEnum.ADMINISTRATOR)
        );
        UserDTO userDTO = new UserDTO(
                1l,
                "test",
                "pass",
                "ADMINISTRATOR"
        );
        UserDTO userDTOByConverter = userConverter.toDTO(user);
        Assert.assertEquals(userDTO.getId(), userDTOByConverter.getId());
        Assert.assertEquals(userDTO.getUsername(), userDTOByConverter.getUsername());
        Assert.assertEquals(userDTO.getPassword(), userDTOByConverter.getPassword());
        Assert.assertEquals(userDTO.getRole(), userDTOByConverter.getRole());
    }

    @Test
    public void fromDTOShouldReturnCorrectData() {
        UserConverter userConverter = new UserConverterImpl();
        UserDTO userDTO = new UserDTO(
                1l,
                "test",
                "pass",
                "ADMINISTRATOR"
        );
        Role role = new Role();
        role.setPermission(PermissionEnum.ADMINISTRATOR);
        User user = new User(
                1l,
                "test",
                "pass",
                role
        );
        User userConverted = userConverter.fromDTO(userDTO);
        Assert.assertEquals(user.getId(), userConverted.getId());
        Assert.assertEquals(user.getUsername(), userConverted.getUsername());
        Assert.assertEquals(user.getPassword(), userConverted.getPassword());
        Assert.assertEquals(user.getRole().getId(), userConverted.getRole().getId());
        Assert.assertEquals(user.getRole().getPermission(), userConverted.getRole().getPermission());
    }
}