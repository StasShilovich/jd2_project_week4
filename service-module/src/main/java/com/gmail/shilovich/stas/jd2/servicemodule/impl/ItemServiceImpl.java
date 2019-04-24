package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.datamodule.ItemRepository;
import com.gmail.shilovich.stas.jd2.datamodule.connection.ConnectorHandler;
import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.datamodule.model.ItemStatusEnum;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemDTOConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemStatusDTOConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.exception.ServiceException;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemStatusDTO;
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
    private static final String ERROR_MESSAGE = "Service module operation failed";

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;
    private final ItemDTOConverter itemDTOConverter;
    private final ItemStatusDTOConverter itemStatusDTOConverter;
    private final ConnectorHandler connectorHandler;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemConverter itemConverter, ItemDTOConverter itemDTOConverter, ItemStatusDTOConverter itemStatusDTOConverter, ConnectorHandler connectorHandler) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
        this.itemDTOConverter = itemDTOConverter;
        this.itemStatusDTOConverter = itemStatusDTOConverter;
        this.connectorHandler = connectorHandler;
    }

    @Override
    public ItemDTO add(ItemDTO item) {
        ItemDTO itemDTO = new ItemDTO();
        try (Connection connection = connectorHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Item itemDAO = itemDTOConverter.fromDTO(itemDTO);
                Item newItem = itemRepository.add(connection, itemDAO);
                itemDTO = itemConverter.toDTO(newItem);
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
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getItems() {
        List<Item> items = null;
        List<ItemDTO> list = null;
        try (Connection connection = connectorHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                items = itemRepository.getItems(connection);
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

    @Override
    public int update(Long id, String status) {
        ItemStatusDTO itemStatusDTO = new ItemStatusDTO(id, ItemStatusEnum.valueOf(status));
        Item item = itemStatusDTOConverter.fromDTO(itemStatusDTO);
        int rowsUpdate = 0;
        try (Connection connection = connectorHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                rowsUpdate = itemRepository.updateItem(connection, item);
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

        return rowsUpdate;
    }
}
