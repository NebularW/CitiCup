package com.flower.service;


import com.flower.Vo.BankCardVo;
import com.flower.Vo.LoginVo;
import com.flower.Vo.RegisterVo;
import com.flower.service.entity.BankCard;
import com.flower.service.entity.User;
import com.flower.result.Result;

import java.util.List;

import java.util.List;

/**
 * @title: UserService
 * @Author: Stanton JY
 * @Date: 2022/2/19 0:23
 */

public interface UserService {
    /**
     * 添加用户信息
     *
     * @param person
     * @return
     */

    Result add(User person);


    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    Result login(LoginVo loginVo);

    Result updateBankInfo(BankCard bankCard);

    Result register(RegisterVo registerVo);
}

