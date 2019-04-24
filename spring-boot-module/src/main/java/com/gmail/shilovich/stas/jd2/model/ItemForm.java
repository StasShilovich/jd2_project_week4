package com.gmail.shilovich.stas.jd2.model;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Size;

public class ItemForm {

    @NotNull
    @Size(min=4,max=20)
    private String name;

    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemForm{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
