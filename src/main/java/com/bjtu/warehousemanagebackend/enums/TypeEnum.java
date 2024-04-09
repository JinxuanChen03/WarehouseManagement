package com.bjtu.warehousemanagebackend.enums;

public enum TypeEnum {
    TYPE_A(1,"TYPE_A"),
    TYPE_B(2,"TYPE_B"),
    TYPE_C(3,"TYPE_C");

    private Integer per;
    private String desc;

    TypeEnum(Integer per, String desc){
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
