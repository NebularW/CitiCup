package com.flower.controller;

import com.flower.Vo.LoginVo;
import com.flower.Vo.RegisterVo;
import com.flower.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @title: LoginControllerTest
 * @Author: Stanton JY
 * @Date: 2022/3/8 22:58
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class LoginControllerTest {
    @Autowired
    LoginController controller;


    @Test
    public void loginTest() {
        Result res = controller.login(new LoginVo("wx1", "ross", "213412"));
        Assertions.assertTrue(res.isFlag());
    }

    @Test
    public void registerTest() {
        Result res=controller.register(new RegisterVo("daoTest2","dao","xx.jpg",0,"13775332202"));
        Assertions.assertTrue(res.isFlag());
    }
    //@Test
    //public void card(){
    //    BankCard card=controller.hasBankCard("o_P505VQk1enxptHjOTx3NMkHQZM");
    //    Assert.assertEquals(card.getOwnerName(),"沈霁昀");
    //}
}
