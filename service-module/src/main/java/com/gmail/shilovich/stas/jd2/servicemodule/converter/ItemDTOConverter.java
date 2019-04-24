package com.gmail.shilovich.stas.jd2.servicemodule.converter;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;

public interface ItemDTOConverter {
    Item fromDTO(ItemDTO itemDTO);
}
