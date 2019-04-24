package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemStatusDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemConverterImpl implements ItemConverter {
    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setItemStatusEnum(item.getItemStatusEnum());
        return itemDTO;
    }

    @Override
    public ItemStatusDTO toStatusDTO(Item item) {
        ItemStatusDTO itemStatusDTO = new ItemStatusDTO();
        itemStatusDTO.setId(item.getId());
        itemStatusDTO.setItemStatusEnum(item.getItemStatusEnum());
        return itemStatusDTO;
    }
}
