package com.gmail.shilovich.stas.jd2.servicemodule.converter;

import com.gmail.shilovich.stas.jd2.datamodule.model.User;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;

public interface UserConverter {
    UserDTO toDTO(User user);

    User fromDTO(UserDTO userDTO);
}
