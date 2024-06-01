package com.bjtu.warehousemanagebackend.domain;

import lombok.Data;

@Data
public class Event {
    private String type;
    private Object data;

    public Event() {}

    public Event(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    // Getters and setters
}