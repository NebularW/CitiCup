package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.Vo.RegisterVo;
import com.flower.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: UserDaoTest
 * @Description: 用于测试UserDao
 * @Author: sky
 * @Date: 2022/3/17 16:42
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    public void addUserTest(){
        RegisterVo registerVo = new RegisterVo("daoTest6","测试UserDao6","",1,"13775339999");
        userDao.addUser(registerVo);
    }

    @Test
    public void updateFamilyIDByIdentityTest(){
        userDao.updateFamilyIDByIdentity("daoTest6",999);
    }

    @Test
    public void updateRiskLevelByIdentityTest(){
        userDao.updateRiskLevelByIdentity(1,"daoTest5");
    }

    @Test
    public void updateFrequencyByIdentityTest(){
        userDao.updateFrequencyByIdentity("daoTest5",5);
    }

    @Test
    public void updateLimitByIdentityTest(){
        userDao.updateLimitByIdentity("daoTest5",100.0);
    }

    @Test
    public void selectByIdentityTest(){
        System.out.println(userDao.selectByIdentity("daoTest5"));
    }

    @Test
    public void findIdentityByPhoneNumberTest(){
        System.out.println(userDao.findIdentityByPhoneNumber("13775336666"));
    }

    @Test
    public void selectById_1Test(){
        System.out.println(userDao.selectById_1("daoTest5"));
    }

    @Test
    public void selectById_2Test(){
        System.out.println(userDao.selectById_2("userDao5"));
    }

}
