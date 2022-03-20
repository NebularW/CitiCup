package com.flower.service.entity;

import lombok.Data;

@Data
public class ElderHelpRequest {

    private String fromIdentity; //请求授权的人

    private String toIdentity; //被请求授权的人

    private boolean isAuthorized;
}
