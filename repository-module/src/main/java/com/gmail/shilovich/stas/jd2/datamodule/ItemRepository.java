package com.gmail.shilovich.stas.jd2.datamodule;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemRepository {
    List<Item> getItems(Connection connection);
}
