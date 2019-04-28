package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.datamodule.model.Item;
import com.gmail.shilovich.stas.jd2.datamodule.model.ItemStatusEnum;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import org.junit.Assert;
import org.junit.Test;

public class ItemConverterImplTest {

    @Test
    public void toDTOShouldReturnCorrectData() {
        ItemConverter itemConverter = new ItemConverterImpl();
        Item item = new Item(
                1l,
                "Item",
                ItemStatusEnum.GO,
                false
        );
        ItemDTO itemDTO = new ItemDTO(
                1l,
                "Item",
                "GO"
        );
        ItemDTO itemDTOConverted = itemConverter.toDTO(item);
        Assert.assertEquals(itemDTO.getId(), itemDTOConverted.getId());
        Assert.assertEquals(itemDTO.getName(), itemDTOConverted.getName());
        Assert.assertEquals(itemDTO.getStatus(), itemDTOConverted.getStatus());
    }
}