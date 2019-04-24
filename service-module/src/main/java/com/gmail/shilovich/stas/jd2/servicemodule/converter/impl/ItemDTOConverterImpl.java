package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemDTOConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemDTOConverterImpl implements ItemDTOConverter {
    @Override
    public Item fromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setItemStatusEnum(itemDTO.getItemStatusEnum());
        return item;
    }
}
