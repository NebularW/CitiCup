package com.flower.service.entity;

import lombok.Data;

/**
 * @ClassName: SellingRecord
 * @Description: 用于记录用户卖出操作
 * @Author: sky
 * @Date: 2022/3/6 14:22
 **/
@Data
public class SellingRecord {
    private Integer id; //primary key

    private String identity; //投资人openid

    private String timestamp; //交易时间（格式位2022-03-03）

    private Double amount; //卖出的金额

    private boolean isExempted; //是否被豁免
}
