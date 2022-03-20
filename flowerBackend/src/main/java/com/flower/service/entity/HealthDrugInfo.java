package com.flower.service.entity;

import lombok.Data;

@Data
public class HealthDrugInfo {
    private Integer id;

    private String identity; //openID

    private String drugName; //药物名称

    private String timestamp; //药物使用时间

    public HealthDrugInfo(String identity, String drugName, String timestamp){
        this.identity = identity;
        this.drugName = drugName;
        this.timestamp = timestamp;
    }

    public HealthDrugInfo(){

    }
}
