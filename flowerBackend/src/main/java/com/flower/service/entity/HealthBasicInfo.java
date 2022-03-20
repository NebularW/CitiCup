package com.flower.service.entity;

import lombok.Data;

@Data
public class HealthBasicInfo {
    private String identity; //openid

    private String name; //姓名

    private Integer age; //年龄

    private Integer sex; //性别

    private String IDCardNumber; //身份证号

    private Double height; //身高 单位 --> cm

    private Double weight; //体重 单位 --> kg

    public HealthBasicInfo(String identity, String name, Integer age, Integer sex, String IDCardNumber, Double height, Double weight){
        this.identity = identity;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.IDCardNumber = IDCardNumber;
        this.height = height;
        this.weight = weight;
    }

    public HealthBasicInfo() {

    }
}
