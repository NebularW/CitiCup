package com.flower.service.Impl;

import com.flower.Vo.BankCardVo;
import com.flower.Vo.LoginVo;
import com.flower.Vo.RegisterVo;
import com.flower.dao.BankCardDao;
import com.flower.dao.UserDao;
import com.flower.service.entity.BankCard;
import com.flower.service.entity.User;
import com.flower.result.Result;
import com.flower.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @title: UserServiceImpl
 * @Author: Stanton JY
 * @Date: 2022/2/24 17:38
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BankCardDao bankCardDao;
    User user;

    @Override
    public Result add(User person) {
        return null;
    }


    @Override
    public Result login(LoginVo loginVo) {
        user = userDao.selectByIdentity(loginVo.getIdentity());
        if (null == user) {//新用户
            return Result.fail("新用户");
        } else {//老用户
            return Result.success("用户：" + user.getIdentity() + "登录");
        }
    }

    @Override
    public Result updateBankInfo(BankCard bankCard) {
        bankCardDao.insertBankCard(bankCard.getOwnerIdentity(), bankCard.getCardID(), bankCard.getOwnerName(), bankCard.getPhoneNumber());
        return Result.success("添加成功");
    }


    @Override
    public Result register(RegisterVo registerVo) {
        try {
            userDao.addUser(registerVo);
            return Result.success("注册成功");
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return Result.fail("用户openid已存在！");
        }
    }
}