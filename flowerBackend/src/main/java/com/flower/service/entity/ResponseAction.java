package com.flower.service.entity;

import lombok.Data;

@Data
public class ResponseAction {

    private String fromIdentity; //执行回应的风险行为人的家人的identity

    private String toIdentity; //风险行为人的identity

    private Integer actionType; //0表示refuse, 1表示accept
}
