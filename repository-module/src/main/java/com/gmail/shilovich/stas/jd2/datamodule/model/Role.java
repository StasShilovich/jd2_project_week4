package com.gmail.shilovich.stas.jd2.datamodule.model;

public class Role {
    private Long id;
    private PermissionEnum permission;

    public Role() {
    }

    public Role(Long id, PermissionEnum permission) {
        this.id = id;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionEnum getPermission() {
        return permission;
    }

    public void setPermission(PermissionEnum permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", permission=" + permission +
                '}';
    }
}
