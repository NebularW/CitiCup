package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.AuthorizationForMemberCardDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: AuthorizationForMemberCardDaoTest
 * @Description: 用于测试AuthorizationForMemberCardDao
 * @Author: sky
 * @Date: 2022/3/17 16:38
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class AuthorizationForMemberCardDaoTest {
    @Autowired
    AuthorizationForMemberCardDao authorizationForMemberCardDao;

    @Test
    public void findAllByFromAndToTest(){
        System.out.println(authorizationForMemberCardDao.findAllByFromAndTo("daoTest1","daoTest2"));
    }

    @Test
    public void addAuthorizationRequestTest(){
//        authorizationForMemberCardDao.addAuthorizationRequest("daoTest2","daoTest1");
    }

    @Test
    public void updateIsAuthorizedByFromAndToTest(){
        authorizationForMemberCardDao.updateIsAuthorizedByFromAndTo("daoTest2","daoTest1",true);
    }

}
