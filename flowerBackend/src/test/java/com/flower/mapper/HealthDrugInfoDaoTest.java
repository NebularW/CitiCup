package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.HealthDrugInfoDao;
import com.flower.service.entity.HealthDrugInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @ClassName: HealthDrugInfoDaoTest
 * @Description: 测试HealthDrugInfoDao
 * @Author: sky
 * @Date: 2022/3/16 22:30
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class HealthDrugInfoDaoTest {
    @Autowired
    HealthDrugInfoDao healthDrugInfoDao;

    @Test
    public void InsertHealthDrugInfoTest(){
        HealthDrugInfo healthDrugInfo = new HealthDrugInfo("daoTest1","万艾可","2022-03-16");
        healthDrugInfoDao.insert(healthDrugInfo);
    }

    @Test
    public void findByOpenIDTest(){
        List<HealthDrugInfo> healthDrugInfoList = healthDrugInfoDao.findByOpenID("daoTest1");
//        Assertions.assertEquals(1,healthDrugInfoList.size());
        System.out.println(healthDrugInfoDao.findByOpenID("daoTest1"));
    }

}
