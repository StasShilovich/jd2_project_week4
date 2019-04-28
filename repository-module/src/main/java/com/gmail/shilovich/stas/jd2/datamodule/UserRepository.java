package com.gmail.shilovich.stas.jd2.datamodule;

import com.gmail.shilovich.stas.jd2.datamodule.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository {
    List<User> getUsers(Connection connection);

    void add(Connection connection, User user);
}
