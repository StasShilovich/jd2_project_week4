package com.gmail.shilovich.stas.jd2.servicemodule.converter;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemStatusDTO;

public interface ItemConverter {
    ItemDTO toDTO(Item item);
    ItemStatusDTO toStatusDTO(Item item);
}
