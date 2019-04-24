package com.gmail.shilovich.stas.jd2.servicemodule.model;

import com.gmail.shilovich.stas.jd2.datamodule.model.ItemStatusEnum;

public class ItemDTO {
    private Long id;
    private String name;
    private ItemStatusEnum itemStatusEnum;

    public ItemDTO(Long id, String name, ItemStatusEnum itemStatusEnum) {
        this.id = id;
        this.name = name;
        this.itemStatusEnum = itemStatusEnum;
    }

    public ItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStatusEnum getItemStatusEnum() {
        return itemStatusEnum;
    }

    public void setItemStatusEnum(ItemStatusEnum itemStatusEnum) {
        this.itemStatusEnum = itemStatusEnum;
    }
}
