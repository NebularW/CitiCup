package com.flower.service.entity;

import lombok.Data;

/**
 * 这个实体类用于记录：每天计算各个类型的基金的收益值
 */
@Data
public class InvestedThingsDailyBenefits {

    private Integer id;

    private String ownerIdentity; //本次记录的基金所有者的openid

    private String fundName; //本次记录的基金名字

    private String category; //本次记录的基金类型

    private String timeStamp; //本次记录的时间戳 格式统一为2022-03-03

    private double benefit; //本类型的基金在本天计算得到的收益

}


