package com.gmail.shilovich.stas.jd2.servicemodule;

import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    void add(UserDTO user);
}
