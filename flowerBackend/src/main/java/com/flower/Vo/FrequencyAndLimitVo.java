package com.flower.Vo;

import lombok.Data;

/**
 * @ClassName: FrequencyAndLimitVo
 * @Description: 封装户交易频率上限和额度上限
 * @Author: sky
 * @Date: 2022/3/5 15:39
 **/
@Data
public class FrequencyAndLimitVo {
    private String identity;    //openid

    private Integer frequency;  //频率上限（以基金为单位）

    private Double limit;       // 额度上限(精确到2位小数)

    public FrequencyAndLimitVo(String identity, Integer frequency, Double limit) {
        this.identity = identity;
        this.frequency = frequency;
        this.limit = limit;
    }
}
