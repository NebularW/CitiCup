package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.RiskActivityDao;
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
 * @ClassName: RiskActivityDaoTest
 * @Description: 测试RiskActivityDa
 * @Author: sky
 * @Date: 2022/3/16 22:40
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class RiskActivityDaoTest {
    @Autowired
    RiskActivityDao riskActivityDao;

    @Test
    public void insertRiskActivityTest(){
        riskActivityDao.insertRiskActivity("daoTest1",138,"2022-03-16",1000.00,"用于daoTest");
    }

    @Test
    public void updateDescription(){
        riskActivityDao.updateDescription("daoTest1","更新Description");
    }

    @Test
    public void selectByIdentity(){
        System.out.println(riskActivityDao.selectByIdentity("daoTest1"));
    }

    @Test
    public void selectUncompletedByIdentity(){
        System.out.println(riskActivityDao.selectUncompletedByIdentity("daoTest1"));
    }

    @Test
    public void selectByFamilyID(){
        System.out.println(riskActivityDao.selectByFamilyID(138));
    }

    @Test
    public void updateCompletedByIdentity(){
        riskActivityDao.updateCompletedByIdentity(true,"daoTest1");
        Assertions.assertNull(riskActivityDao.selectUncompletedByIdentity("daoTest1"));
    }









}
