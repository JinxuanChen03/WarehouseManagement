package com.bjtu.warehousemanagebackend.enums;

public enum PermissionEnum {
    ROLE_CUSTOMER(1,"ROLE_CUSTOMER"),
    ROLE_PROVIDER(2,"ROLE_PROVIDER"),
    ROLE_ADMIN(3,"ROLE_ADMIN");

    private Integer per;
    private String desc;

    PermissionEnum(Integer per, String desc){
        this.per = per;
        this.desc = desc;
    }

    public String toString(){
        return desc;
    }

    public Integer getValue() {
        return per;
    }
}
