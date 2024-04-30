package com.bjtu.warehousemanagebackend.enums;

import lombok.Getter;

public enum Role {
//    ROLE_CUSTOMER(1,"ROLE_CUSTOMER"),
//    ROLE_PROVIDER(2,"ROLE_PROVIDER"),
//    ROLE_ADMIN(3,"ROLE_ADMIN");

    //超级管理员 不对外提供添加此权限的方法
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN", "超级管理员"),

    ROLE_ADMIN("ROLE_ADMIN", "管理员"),

    ROLE_COMMODITY("ROLE_User", "商品");


    private String per;
    private String desc;

    Role(String per, String desc){
        this.per = per;
        this.desc = desc;
    }

    public String toString(){
        return desc;
    }

    public String getValue() {
        return per;
    }
}
