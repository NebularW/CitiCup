package com.flower.service.entity;

import lombok.Data;

@Data
public class InvestedThingsRecord {
    private Integer id;

    private String identity; //购买者的openid

    private String fundName; //基金名称

    private String category; //股票, or债券 or...

    private Double amount; //投入资金

    private String timeStamp; //交易时间戳

    private boolean isExempted = false; //是否被豁免

    private Integer status; //0: buy in; 1: sell out
}
