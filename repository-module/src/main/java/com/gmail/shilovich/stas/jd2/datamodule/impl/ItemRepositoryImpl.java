package com.gmail.shilovich.stas.jd2.datamodule.impl;

import com.gmail.shilovich.stas.jd2.datamodule.ItemRepository;
import com.gmail.shilovich.stas.jd2.datamodule.connection.ConnectorHandler;
import com.gmail.shilovich.stas.jd2.datamodule.exception.DatabaseException;
import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.datamodule.model.ItemStatusEnum;
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
public class ItemRepositoryImpl implements ItemRepository {

    private static final Logger logger = LogManager.getLogger(ConnectorHandler.class);
    private static final String ERROR_MESSAGE = "Data module operation failed";

    private final ConnectorHandler connectorHandler;

    @Autowired
    public ItemRepositoryImpl(ConnectorHandler connectorHandler) {
        this.connectorHandler = connectorHandler;
    }

    @Override
    public List<Item> getItems(Connection connection) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM t_item";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Item item = getItem(set);
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DatabaseException(ERROR_MESSAGE);
        }
        return items;
    }

    private Item getItem(ResultSet set) throws SQLException {
        Item item = new Item();
        item.setId(set.getLong("f_id"));
        item.setName(set.getString("f_name"));
        String status = set.getString("f_status").toUpperCase();
        item.setItemStatusEnum(ItemStatusEnum.valueOf(status));
        int deleted = set.getInt("f_deleted");
        if (deleted == 0) {
            item.setDeleted(false);
        } else {
            item.setDeleted(true);
        }
        return item;
    }
}
