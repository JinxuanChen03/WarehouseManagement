package com.bjtu.warehousemanagebackend.enums;

public enum Role {
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN", "超级管理员"),

    ROLE_ADMIN("ROLE_ADMIN", "管理员"),

    ROLE_USER("ROLE_USER", "普通用户");


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
