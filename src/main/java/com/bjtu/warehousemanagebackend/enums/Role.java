package com.bjtu.warehousemanagebackend.enums;

import lombok.Getter;

public enum Role {
//    ROLE_CUSTOMER(1,"ROLE_CUSTOMER"),
//    ROLE_PROVIDER(2,"ROLE_PROVIDER"),
//    ROLE_ADMIN(3,"ROLE_ADMIN");

    //超级管理员 不对外提供添加此权限的方法
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN", "超级管理员"),

    ROLE_ADMIN("ROLE_ADMIN", "管理员"),

    ROLE_COMMODITY("ROLE_COMMODITY", "商品"),

    ROLE_EMPLOYEE("ROLE_EMPLOYEE", "员工"),

    ROLE_SALE("ROLE_SALE", "销售"),

    ROLE_WAREHOUSE("ROLE_WAREHOUSE", "仓库");

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
