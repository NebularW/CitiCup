package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.InvestedThingsDailyBenefitsDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: InvestedThingsDailyBenefitsDaoTest
 * @Description: 用于测试InvestedThingsDailyBenefitsDao
 * @Author: sky
 * @Date: 2022/3/17 0:12
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class InvestedThingsDailyBenefitsDaoTest {
    @Autowired
    InvestedThingsDailyBenefitsDao investedThingsDailyBenefitsDao;
    String identity = "daoTest1";
    String timestamp = "2022-03-16";

    @Test
    public void findBenefitsYesterdaySumByOwnerIdentityAndTimeStampTest(){
        System.out.println(investedThingsDailyBenefitsDao.findBenefitsYesterdaySumByOwnerIdentityAndTimeStamp(identity,timestamp));
    }

    @Test
    public void findBenefitsSumByOwnerIdentityAndTimeStampTest(){
        System.out.println(investedThingsDailyBenefitsDao.findBenefitsSumByOwnerIdentityAndTimeStamp(identity));
    }

    @Test
    public void findBenefitsDaySumByOwnerIdentityAndTimeStampTest(){
        System.out.println(investedThingsDailyBenefitsDao.findBenefitsDaySumByOwnerIdentityAndTimeStamp(identity,timestamp));
    }

    @Test
    public void findAllByIdentityTest(){
        System.out.println(investedThingsDailyBenefitsDao.findAllByIdentity(identity));
    }

    @Test
    public void findByIdentityAndTimestampTest(){
        System.out.println(investedThingsDailyBenefitsDao.findByIdentityAndTimestamp(identity,timestamp));
    }






}
