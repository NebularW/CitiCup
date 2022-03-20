package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.HealthSugarLevelBloodPressureHeartRateDao;
import com.flower.service.entity.HealthSugarLevelBloodPressureHeartRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: HealthSugarLevelBloodPressureHeartRateDaoTest
 * @Description: 用于测试HealthSugarLevelBloodPressureHeartRateDao
 * @Author: sky
 * @Date: 2022/3/16 23:34
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class HealthSugarLevelBloodPressureHeartRateDaoTest {
    @Autowired
    HealthSugarLevelBloodPressureHeartRateDao healthSugarLevelBloodPressureHeartRateDao;
    HealthSugarLevelBloodPressureHeartRate healthSugarLevelBloodPressureHeartRate = new HealthSugarLevelBloodPressureHeartRate("daoTest1",0,60.0,"2022-03-16");

    @Test
    public void insertTest(){
        healthSugarLevelBloodPressureHeartRateDao.insert(healthSugarLevelBloodPressureHeartRate);
    }

    @Test
    public void findByTypeAndOpenIDTest(){
        System.out.println(healthSugarLevelBloodPressureHeartRateDao.findByTypeAndOpenID(0,"daoTest1"));
    }


}
