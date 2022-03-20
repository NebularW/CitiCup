package com.flower.Vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @title: LoginVo
 * @Author: Stanton JY
 * @Date: 2022/2/19 0:52
 */

@Data
public class LoginVo {
    private String identity; //code

    private String userName; //用户名

    private String avatarUrl; //头像地址

    public LoginVo(String openid, String userName, String avatarUrl) {
        this.identity = openid;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
    }
}

