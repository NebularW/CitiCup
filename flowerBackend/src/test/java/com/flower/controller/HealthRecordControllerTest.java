package com.flower.controller;

import com.flower.FlowerBackendApplication;
import com.flower.dao.UserDao;
import com.flower.service.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class HealthRecordControllerTest {

    @Autowired
    private HealthRecordController healthRecordController;

    @Autowired
    UserDao userDao;

    List<User> users=new ArrayList<>();
    User user1=new User();
    User user2=new User();
    User user3=new User();
    User user4=new User();
    User user5=new User();
    User user6=new User();
    User user7=new User();
    User user8=new User();
    User user9=new User();
    User user10=new User();







    public void createTestCase(){
        user1=userDao.selectByIdentity("wx1");
        user2=userDao.selectByIdentity("wx2");
        user3=userDao.selectByIdentity("wx3");
        user4=userDao.selectByIdentity("wx4");
        user5=userDao.selectByIdentity("wx5");
        user6=userDao.selectByIdentity("wx6");
        user7=userDao.selectByIdentity("wx7");
        user8=userDao.selectByIdentity("wx10");

        user9.setIdentity("oo");
        user9.setPhoneNumber("13900000000");
        user9.setSex(0);
        user9.setUserName("lxy");

        user9.setIdentity("ooo");
        user9.setPhoneNumber("13900000001");
        user9.setSex(1);
        user9.setUserName("lyx");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

    }





    //  3种情况已测试成功 by-lxy 可再测试
    @Test
    public void addBasicInfo(){
        createTestCase();
        int i=0;
        for(User user:users) {
            healthRecordController.addBasicInfo(user.getIdentity(), "cat", 24, 0, "3456445654"+ i, 178.0, 54.1);
            i++;
        }
    }

    //  3种情况已测试成功 by-lxy 可再测试
    @Test
    public void updateBasicInfo(){
        createTestCase();
        int i=0;
        for(User user:users) {
            healthRecordController.updateBasicInfo(user.getIdentity(), "dog", 24, 0, "3456445654"+ i, 178.0, 54.1);
            i++;

        }

    }

    //success
    @Test
    public void getBasicInfo(){
        createTestCase();
        for(User user:users) {
            HealthBasicInfo healthBasicInfo = healthRecordController.getBasicInfo(user.getIdentity());
            System.out.println(healthBasicInfo);
        }


    }


    //日期必须在前端必须转化为2022-3-13格式，否则数据库date解析会出错,已经测试完毕

    @Test
    public void insertBloodInfo(){
        createTestCase();
        for(User user:users) {
            healthRecordController.insertBloodInfo(user.getIdentity(), 0, 90.5, "2022-3-15");
        }
    }

    //    success
    @Test
    public void getBloodInfo(){
        createTestCase();
        for(User user:users) {
            List<HealthSugarLevelBloodPressureHeartRate> li = healthRecordController.getBloodInfo(user.getIdentity(), 0);
            System.out.println(li);
        }

    }

    //    success
    @Test
    public void addMedicineInfo(){
        createTestCase();
        for(User user:users) {
            healthRecordController.addMedicineInfo(user.getIdentity(), "阿莫西林", "2022-4-16");
        }

    }


    //    success
    @Test
    public void getMedicineInfo(){
        createTestCase();
        for(User user:users) {
            List<HealthDrugInfo> li = healthRecordController.getMedicineInfo(user.getIdentity());
            System.out.println(li);
        }


    }

    //    success
    @Test
    public void addMedicalHistoryInfo(){
        createTestCase();
        for(User user:users) {
            healthRecordController.addMedicalHistoryInfo(user.getIdentity(), "糖尿病", true, "少糖", "2022-3-14");
        }
    }

    //    success
    @Test
    public void getMedicalHistoryInfo(){
        createTestCase();
        for(User user:users) {
            List<HealthMedicalHistory> li = healthRecordController.getMedicalHistoryInfo(user.getIdentity());
            System.out.println(li);
        }



    }






}
