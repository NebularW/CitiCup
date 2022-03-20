package com.flower.Vo;

import lombok.Data;

/**
 * @title: RegisterVo
 * @Author: Stanton JY
 * @Date: 2022/3/6 18:45
 */
@Data
public class RegisterVo {
    private String identity;
    private String userName; //用户名
    private String avatarUrl; //头像地址
    private Integer gender;
    private String phone;

    public RegisterVo(String identity, String userName, String avatarUrl, Integer gender, String phone) {
        this.identity = identity;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.phone = phone;
    }

    public RegisterVo() {
    }
}
