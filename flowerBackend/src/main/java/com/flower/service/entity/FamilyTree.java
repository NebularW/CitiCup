package com.flower.service.entity;

import lombok.Data;

import java.util.List;

@Data
public class FamilyTree {
    private Integer id; //primary key

    private String houseHolderIdentity; //户主openid

    private String familyName; //家庭的名称

    private Integer credits; //家庭积分

    private Integer memberNumber; //成员个数

    private Integer redLevelNumber; //家庭中处于红色风险等级的成员个数

}

