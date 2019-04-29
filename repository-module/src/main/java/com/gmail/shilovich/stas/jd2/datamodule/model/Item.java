package com.gmail.shilovich.stas.jd2.datamodule.model;

public class Item {
    private Long id;
    private String name;
    private ItemStatusEnum itemStatusEnum;
    private Boolean deleted;

    public Item(Long id, String name, ItemStatusEnum itemStatusEnum, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.itemStatusEnum = itemStatusEnum;
        this.deleted = deleted;
    }

    public Item() {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemStatusEnum=" + itemStatusEnum +
                '}';
    }
}
