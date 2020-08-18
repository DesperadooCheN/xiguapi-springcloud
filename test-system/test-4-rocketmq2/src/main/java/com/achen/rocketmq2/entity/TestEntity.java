package com.achen.rocketmq2.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestEntity implements Serializable {
    private String orderId;
    private String name;

    public TestEntity(String orderId, String name) {
        this.orderId = orderId;
        this.name = name;
    }
}
