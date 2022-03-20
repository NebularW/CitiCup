package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.InvestedThingsRecordsDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @ClassName: InvestedThingsRecordsDaoTest
 * @Description: 用于测试InvestedThingsRecordsDao
 * @Author: sky
 * @Date: 2022/3/17 0:10
 **/
@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class InvestedThingsRecordsDaoTest {
    @Autowired
    InvestedThingsRecordsDao investedThingsRecordsDao;
    String identity = "daoTest1";
    String timestamp = "2022-03-16";

    @Test
    public void selectByIdentityTest(){
        System.out.println(investedThingsRecordsDao.selectByIdentity(identity));
    }

    @Test
    public void insertInvestedThingsRecord(){
        investedThingsRecordsDao.insertInvestedThingsRecord(identity,"测试用股票","股票",20.00,timestamp,true,0);
    }

    @Test
    public void findByTimestampAndIdentity(){
        System.out.println(investedThingsRecordsDao.findByTimestampAndIdentity(timestamp,identity));
    }

}
