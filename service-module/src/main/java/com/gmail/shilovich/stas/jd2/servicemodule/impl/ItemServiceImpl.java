package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.datamodule.ItemRepository;
import com.gmail.shilovich.stas.jd2.datamodule.connection.ConnectorHandler;
import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.exception.ServiceException;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    private static final String ERROR_MESSAGE = "Item Service module operation failed";

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;
    private final ConnectorHandler connectorHandler;

    @Autowired
    public ItemServiceImpl(
            ItemRepository itemRepository,
            ItemConverter itemConverter,
            ConnectorHandler connectorHandler
    ) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
        this.connectorHandler = connectorHandler;
    }

    @Override
    public List<ItemDTO> getItems() {
        List<ItemDTO> list = null;
        try (Connection connection = connectorHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Item> items = itemRepository.getItems(connection);
                list = items.stream()
                        .map(itemConverter::toDTO)
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
        return list;
    }
}
