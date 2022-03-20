package com.flower.controller;

import com.flower.FlowerBackendApplication;
import com.flower.Vo.HomePageVo;
import com.flower.Vo.UserVo1;
import com.flower.Vo.UserVo2;

import com.flower.dao.UserDao;
import com.flower.service.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @title: FamilyControllerTest
 * @Author: Stanton JY
 * @Date: 2022/3/6 0:40
 */
@SpringBootTest(classes= FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class FamilyControllerTest {
    @Autowired
    FamilySelectedController controller;

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






    @Test
    public void createFamilyTest(){
        createTestCase();
        int i=0;
        for(User user:users){
            controller.createFamily(user.getIdentity(),"test"+ i);
        }

    }




    //3种情况经测试无误--by-lxy--有问题可联系
    @Test
    public void inFamilyTest() {
        createTestCase();
        for(User user:users) {
            Integer res = controller.inFamily(user.getIdentity());
            if (res == 0) {
                System.out.println("不存在该用户或者该用户不在某一个家庭里");
            } else {
                System.out.println("Find!");
            }
        }



    }


    //3种情况经测试无误--by-lxy--有问题可联系
    @Test
    public void getFamilyIDTest() {
        createTestCase();
        for(User user:users) {
            Integer res = controller.getFamilyID(user.getIdentity());
            if (res == null) {
                System.out.println("False identity or not in a family!");
            } else {
                System.out.println(res);
            }
        }
    }

    //经测试无误，by-lxy...
    @Test
    public void getUsersTest() {
        createTestCase();
        for(User user:users) {
            List<String> res = controller.getUsers(user.getIdentity());
            if (res == null) {
                System.out.println("False identity!");
            } else {
                System.out.println(res);
            }
        }
    }

//经测试无误--by-lxy...
    @Test
    public void getAllMembersTest() {
        createTestCase();
        for(User user:users) {
            List<UserVo2> res = controller.getAllMembers(user.getIdentity());
            if (res == null) {
                System.out.println("False identity!");
            } else {
                System.out.println(res);
            }
        }
    }

    //由于没有家庭表，到家庭部分之前经测试无误 by-lxy
    @Test
    public void addFamilyMemberTest() {
        createTestCase();

        controller.addFamilyMember("18945672347",16);
        controller.addFamilyMember("13956986325",user1.getFamilyID());



    }


    //经测试无误 by-lxy
    @Test
    public void deleteFamilyMemberTest() {

        controller.deleteFamilyMember("wx1",userDao.selectByIdentity("wx1").getFamilyID());

    }


    // 无数据部分测试无误 by-lxy
    @Test
    public void requestAuthTest() {

        controller.requestAuth("12321423","26322556");

    }


    //经测试无误 by-lxy
    @Test
    public void userGetTest() {
        createTestCase();
        for(User user:users) {
            UserVo1 res = controller.userGet(user.getIdentity());
            if (res == null) {
                System.out.println("False identity!");
            } else {
                System.out.println(res);
            }
        }

    }


    //经测试无误 by-lxy
    @Test
    public void singleAuthorizationTest() {
        Integer res = controller.singleAuthorization("12321423","12312");
        System.out.println(res);

    }


    //经测试无误 by-lxy
    @Test
    public void allRelationTest() {
        createTestCase();
        for(User user:users) {
            Map<String, Integer> res = controller.allRelation(user.getIdentity());
            if (res == null) {
                System.out.println("False identity!");
            } else {
                System.out.println(res);
            }
        }
    }



    @Test
    public void isHouseHolderTest(){
        createTestCase();
        for(User user:users) {
            Boolean flag = controller.isHouseHolder("111");
            System.out.println(flag);
        }
    }

    @Test
    public void familyMemberPortfolioTest(){
        HomePageVo homePageInfo=controller.familyMemberPortfolio("111","222");
        System.out.println(homePageInfo);

    }



    //经测试，已经删除成功
    @Test
    public void deleteFamilyTest(){
        createTestCase();
        for(User user:users) {
            controller.deleteFamily(user.getIdentity());
        }
    }








}
