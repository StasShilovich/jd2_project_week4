package com.gmail.shilovich.stas.jd2.datamodule.impl;

import com.gmail.shilovich.stas.jd2.datamodule.UserRepository;
import com.gmail.shilovich.stas.jd2.datamodule.connection.ConnectorHandler;
import com.gmail.shilovich.stas.jd2.datamodule.exception.DatabaseException;
import com.gmail.shilovich.stas.jd2.datamodule.model.PermissionEnum;
import com.gmail.shilovich.stas.jd2.datamodule.model.Role;
import com.gmail.shilovich.stas.jd2.datamodule.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LogManager.getLogger(ConnectorHandler.class);
    private static final String ERROR_MESSAGE = "Data module operation failed";

    private final ConnectorHandler connectorHandler;

    @Autowired
    public UserRepositoryImpl(ConnectorHandler connectorHandler) {
        this.connectorHandler = connectorHandler;
    }

    @Override
    public List<User> getUsers(Connection connection) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.f_id, u.f_name,u.f_password, per.f_name FROM t_user u JOIN t_role r on u.f_id = r.f_id JOIN t_role_permission rp on r.f_id = rp.f_role_id JOIN t_permission per on rp.f_role_id = per.f_id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    User user = new User();
                    user.setId(set.getLong("u.f_id"));
                    user.setUsername(set.getString("u.f_name"));
                    user.setPassword(set.getString("u.f_password"));
                    Role role = new Role();
                    role.setPermission(PermissionEnum.valueOf(set.getString("per.f_name").toUpperCase()));
                    user.setRole(role);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DatabaseException(ERROR_MESSAGE);
        }
        return users;
    }

    @Override
    public void add(Connection connection, User user) {
        String sql = "INSERT INTO t_user(f_name,f_password,f_role_id) VALUES (?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().getPermission().name());
            int update = statement.executeUpdate();
            if (update == 0) {
                logger.error("Adding user error. Affected rows: " + update);
                throw new DatabaseException(ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException(ERROR_MESSAGE);
        }
    }
}
