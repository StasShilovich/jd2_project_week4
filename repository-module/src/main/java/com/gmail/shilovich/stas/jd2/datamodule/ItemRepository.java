package com.gmail.shilovich.stas.jd2.datamodule;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemRepository {
    Item add(Connection connection, Item item);

    List<Item> getItems(Connection connection);

    int updateItem(Connection connection, Item item);
}
