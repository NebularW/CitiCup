package com.flower.service.entity;

import lombok.Data;

@Data
public class RiskActivity {
    private Integer id; //在数据库中的Id

    private String identity; //执行风险活动的用户的openid

    private Integer familyID; //风险行为人所属的家庭的ID

    private String timeStamp; //风险活动的时间

    private Double amount; //投入资金

    private String description; //风险描述

    private boolean completed; //风险是否解除
}

