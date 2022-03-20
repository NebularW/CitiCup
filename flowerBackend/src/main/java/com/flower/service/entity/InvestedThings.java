package com.flower.service.entity;

import lombok.Data;

@Data
public class InvestedThings {
    private Integer id;

    private String identity; //本次投资基金的投资人openid

    private String fundName; //本次投资基金名

    private String category; //本次投资基金所属类型

    private Double value; //这笔投资基金截至上次计算的总价值（本金+收益）

    private String timeStamp; //这笔投资基金在上次计算value的时间戳

    private Double proportion; //这个类型的基金占本次投资金额的比例
}
