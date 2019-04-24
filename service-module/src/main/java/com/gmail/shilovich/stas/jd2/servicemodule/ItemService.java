package com.gmail.shilovich.stas.jd2.servicemodule;

import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO add(ItemDTO item);

    List<ItemDTO> getItems();

    int update(Long id, String status);
}
