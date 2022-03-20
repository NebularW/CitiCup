package com.flower.service.entity;

import lombok.Data;

/**
 * 该类用于对应：家庭成员点击成员名片后授权查看对方的实时收益和投资组合情况,只有授权了才会在这边记录
 */
@Data
public class AuthorizationForMemberCard {

    private String fromIdentity; //请求授权的人

    private String toIdentity; //被请求授权的人

    private boolean isAuthorized;
}



