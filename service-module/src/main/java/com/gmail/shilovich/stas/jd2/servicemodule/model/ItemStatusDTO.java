package com.gmail.shilovich.stas.jd2.servicemodule.model;

import com.gmail.shilovich.stas.jd2.datamodule.model.ItemStatusEnum;

public class ItemStatusDTO {
    private Long id;
    private ItemStatusEnum itemStatusEnum;

    public ItemStatusDTO(Long id, ItemStatusEnum itemStatusEnum) {
        this.id = id;
        this.itemStatusEnum = itemStatusEnum;
    }

    public ItemStatusDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemStatusEnum getItemStatusEnum() {
        return itemStatusEnum;
    }

    public void setItemStatusEnum(ItemStatusEnum itemStatusEnum) {
        this.itemStatusEnum = itemStatusEnum;
    }
}
