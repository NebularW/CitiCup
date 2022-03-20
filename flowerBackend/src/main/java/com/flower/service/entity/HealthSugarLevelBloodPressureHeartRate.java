package com.flower.service.entity;

import lombok.Data;

@Data
public class HealthSugarLevelBloodPressureHeartRate {
    private Integer id;

    private String identity; //openID

    private Integer type; //0 --> sugar level; 1 --> blood pressure; 2 --> heart rate

    private Double level; //本次记录type的水平

    private String timestamp; //本次记录的时间

    public HealthSugarLevelBloodPressureHeartRate() {
    }

    public HealthSugarLevelBloodPressureHeartRate(String identity, Integer type, Double level, String timestamp) {
        this.identity = identity;
        this.type = type;
        this.level = level;
        this.timestamp = timestamp;
    }
}
