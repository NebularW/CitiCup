package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.ResponseActionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: ResponseActionDaoTest
 * @Description: 用于测试ResponseActionDao
 * @Author: sky
 * @Date: 2022/3/16 23:02
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class ResponseActionDaoTest {
    @Autowired
    ResponseActionDao responseActionDao;

    static String fromIdentity = "daoTest1";
    static String toIdentity = "daoTest2";


    @Test
    public void insertResponseActionTest(){
        responseActionDao.insertResponseAction(fromIdentity,toIdentity,0);
    }

    @Test
    public void selectByToIdentityAndFromIdentityTest(){
        System.out.println(responseActionDao.selectByToIdentityAndFromIdentity(toIdentity,fromIdentity));
    }

    @Test
    public void toIdentityNumTest(){
        System.out.println(responseActionDao.toIdentityNum(toIdentity));
    }



    @Test
    public void isRefusedTest(){
        System.out.println(responseActionDao.isRefused(toIdentity));
    }

    @Test

    public void deleteByToIdentityTest(){
        responseActionDao.deleteByToIdentity(toIdentity);
        Assertions.assertEquals(0,responseActionDao.toIdentityNum(toIdentity));
    }






}
