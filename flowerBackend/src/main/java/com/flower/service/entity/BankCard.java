package com.flower.service.entity;

import lombok.Data;


@Data
public class BankCard {

    private String ownerIdentity; //银行卡持有者openid

    private String cardID; //卡号

    private String ownerName; //持有者姓名

    private String phoneNumber; //银行卡预留电话
}
