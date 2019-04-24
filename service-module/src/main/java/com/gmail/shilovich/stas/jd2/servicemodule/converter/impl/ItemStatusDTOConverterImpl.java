package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemStatusDTOConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemStatusDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemStatusDTOConverterImpl implements ItemStatusDTOConverter {
    @Override
    public Item fromDTO(ItemStatusDTO itemStatusDTO) {
        Item item=new Item();
        item.setId(itemStatusDTO.getId());
        item.setItemStatusEnum(itemStatusDTO.getItemStatusEnum());
        return item;
    }
}
