package com.flower.controller;

import com.flower.Vo.BankCardVo;
import com.flower.Vo.LoginVo;
import com.flower.Vo.RegisterVo;
import com.flower.Vo.hasBankCardVo;
import com.flower.dao.BankCardDao;
import com.flower.result.Result;
import com.flower.service.UserService;
import com.flower.service.entity.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @title: LoginController
 * @Author: Stanton JY
 * @Date: 2022/2/24 23:35
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BankCardDao bankCardDao;

    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        Result result = userService.login(loginVo);
        return result;
    }

    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo) {
        Result result = userService.register(registerVo);
        return result;
    }

    @RequestMapping("/bankcard")
    public Result bankCardInfo(@RequestBody BankCard bankCard) {
        Result result = userService.updateBankInfo(bankCard);
        return result;
    }

    @RequestMapping("/hascard")
    public List<BankCard> hasBankCard(@RequestBody hasBankCardVo id) {
        List<BankCard> card=bankCardDao.findBankCardByOwnerIdentity(id.getIdentity());
        return card;
    }
}
