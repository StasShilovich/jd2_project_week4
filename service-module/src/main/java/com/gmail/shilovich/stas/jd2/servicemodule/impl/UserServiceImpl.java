package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.datamodule.UserRepository;
import com.gmail.shilovich.stas.jd2.datamodule.connection.ConnectorHandler;
import com.gmail.shilovich.stas.jd2.datamodule.model.User;
import com.gmail.shilovich.stas.jd2.servicemodule.UserService;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.UserConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.exception.ServiceException;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    private static final String ERROR_MESSAGE = "User Service module operation failed";

    private final UserRepository userRepository;
    private final ConnectorHandler connectorHandler;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConnectorHandler connectorHandler, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.connectorHandler = connectorHandler;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = null;
        List<UserDTO> userDTOS = null;
        try (Connection connection = connectorHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                users = userRepository.getUsers(connection);
                userDTOS = users.stream()
                        .map(userConverter::toDTO)
                        .collect(Collectors.toList());
                connection.commit();
            } catch (SQLException e) {
                logger.error(ERROR_MESSAGE, e);
                connection.rollback();
                throw new ServiceException(ERROR_MESSAGE, e);
            }
        } catch (SQLException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
        return userDTOS;
    }

    @Override
    public void add(UserDTO user) {
        try (Connection connection = connectorHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User userDTO = userConverter.fromDTO(user);
                userRepository.add(connection, userDTO);
                connection.commit();
            } catch (SQLException e) {
                logger.error(ERROR_MESSAGE, e);
                connection.rollback();
                throw new ServiceException(ERROR_MESSAGE, e);
            }
        } catch (SQLException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
    }
}
