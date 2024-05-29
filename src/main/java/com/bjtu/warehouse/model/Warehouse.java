package com.bjtu.warehouse.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("warehouse")
public class Warehouse {

    @Id
    private String id;

    private String name;

    private String addr;

    private String createAt;

}
