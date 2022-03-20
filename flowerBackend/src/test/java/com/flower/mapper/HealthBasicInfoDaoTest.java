package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.HealthBasicInfoDao;
import com.flower.service.entity.HealthBasicInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: HealthBasicInfoDaoTest
 * @Description: 用于测试HealthBasicDa
 * @Author: sky
 * @Date: 2022/3/16 23:15
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class HealthBasicInfoDaoTest {
    @Autowired
    HealthBasicInfoDao healthBasicInfoDao;

    HealthBasicInfo healthBasicInfo = new HealthBasicInfo("daoTest2","沈某",50,1,"32118",175.1,75.0);


    @Test
    public void insertTest(){
//        healthBasicInfoDao.insert(healthBasicInfo);
    }

    @Test
    public void findAllByOpenIDTest(){
        System.out.println(healthBasicInfoDao.findAllByOpenID("daoTest1"));
    }

    @Test
    public void updateTest(){
        healthBasicInfo.setWeight(0.5);
        healthBasicInfoDao.update(healthBasicInfo);
    }







}
