package com.gmail.shilovich.stas.jd2.datamodule.model;

public enum ItemStatusEnum {
    READY("ready"),
    STEADY("steady"),
    GO("go");

    private String statusName;

    ItemStatusEnum(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
