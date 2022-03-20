package com.flower.Vo;

import lombok.Data;

/**
 * @ClassName: RiskActVo
 * @Description: 封装给前端传递的家庭风险
 * @Author: sky
 * @Date: 2022/3/14 23:41
 **/
@Data
public class RiskActVo {
    private String identity; //open id

    private String userName; //用户名

    private String avatarUrl; //头像地址

    private String timeStamp; //风险活动的时间

    private Double amount; //投入资金

    private String description; //风险描述

    public RiskActVo(String identity,String userName,String avatarUrl,String timeStamp,Double amount,String description){
        this.identity = identity;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.description = description;
    }

}
