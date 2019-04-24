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
import java.sql.Statement;
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
    public Item add(Connection connection, Item item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setItemStatusEnum(item.getItemStatusEnum());
        newItem.setDeleted(item.getDeleted());
        String sql = "INSERT INTO t_item(f_name,f_status,f_deleted) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getItemStatusEnum().getStatusName());
            int deleted = 0;
            if (item.getDeleted()) {
                deleted = 1;
            }
            statement.setString(3, String.valueOf(deleted));
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No rows affected");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newItem.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DatabaseException(ERROR_MESSAGE);
        }
        return newItem;
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

    @Override
    public int updateItem(Connection connection, Item item) {
        int rows = 0;
        String sql = "UPDATE t_item SET f_status=? WHERE f_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getItemStatusEnum().getStatusName());
            statement.setString(2, item.getId().toString());
            rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No rows affected");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DatabaseException(ERROR_MESSAGE);
        }
        return rows;
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
