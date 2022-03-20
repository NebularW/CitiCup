package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.InvestedThingsDao;
import com.flower.service.entity.InvestedThings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: investedThingsDaoTest
 * @Description: 用于测试investedThingsDao
 * @Author: sky
 * @Date: 2022/3/16 23:52
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class investedThingsDaoTest {
    @Autowired
    InvestedThingsDao investedThingsDao;
    InvestedThings investedThings = new InvestedThings();


    @Test
    public void insertTest(){
        investedThingsDao.insert("daoTest1","测试用股票","股票",20.00,"2022-01-14",1.0);
    }


    @Test
    public void findValuesByIdentityTest(){
        System.out.println(investedThingsDao.findValuesByIdentity("daoTest1"));
    }

    @Test
    public void updateValueByIdentityAndFundNameTest(){
        investedThingsDao.updateValueByIdentityAndFundName("daoTest1","测试用股票",100.00);
    }

    @Test
    public void amountSumTest(){
        System.out.println(investedThingsDao.amountSum("daoTest1"));
    }

    @Test
    public void selectAllValidByIdentityTest(){
        System.out.println(investedThingsDao.selectAllValidByIdentity("daoTest1"));
    }

    @Test
    public void deleteByIdentityTest(){
        investedThingsDao.deleteByIdentity("daoTest1");
    }

}
