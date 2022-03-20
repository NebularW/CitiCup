package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.HealthMedicalHistoryDao;
import com.flower.service.entity.HealthMedicalHistory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: HealthMedicalHistoryDaoTest
 * @Description: 用于测试HealthMedicalHistoryDao
 * @Author: sky
 * @Date: 2022/3/16 23:16
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class HealthMedicalHistoryDaoTest {
    @Autowired
    HealthMedicalHistoryDao healthMedicalHistoryDao;
    HealthMedicalHistory healthMedicalHistory = new HealthMedicalHistory("daoTest1","低血糖",false,"少糖","2022-03-11");

    @Test
    public void insertTest(){
        healthMedicalHistoryDao.insert(healthMedicalHistory);
    }

    @Test
    public void findByOpenIDTest(){
        System.out.println(healthMedicalHistoryDao.findByOpenID("daoTest1"));
    }




}
