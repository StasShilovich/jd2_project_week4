package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemConverterImpl implements ItemConverter {
    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setStatus(item.getItemStatusEnum().name());
        return itemDTO;
    }
}
