package com.flower.controller;

import com.flower.Vo.HomePageVo;
import com.flower.Vo.UserVo1;
import com.flower.Vo.UserVo2;
import com.flower.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @Author: lxy
 */


@RestController
@RequestMapping("/family")
public class FamilySelectedController {
   @Autowired
   private FamilyService familyService;

    //判断该用户是否在某个家庭里,0表示不存在这个用户或者说这个用户不再某个家庭里,1表示在

    @GetMapping("/inFamily")
    public Integer inFamily(@RequestParam String identity) {
     return familyService.inFamily(identity);
    }


    //返回该用户的familyID,-1表示该用户不存在，或者说存在但不在某一个家庭里

    @GetMapping("/getFamilyID")
    public Integer getFamilyID(@RequestParam String identity) {
       return familyService.getFamilyID(identity);
    }


    //返回该用户所在家庭的所有成员的openid/identity

    @GetMapping("/allIdentity")
    public List<String> getUsers(@RequestParam String identity) {
       return  familyService.getUsers(identity);
    }

//    返回家庭中所有成员的identity,头像和姓名

    @GetMapping("/allMembers")
    public List<UserVo2> getAllMembers(@RequestParam String identity) {
       return familyService.getAllMembers(identity);

    }

    //删除某一家庭成员

    @PostMapping("/delMember")
    public void deleteFamilyMember(@RequestParam String identity, @RequestParam Integer familyID) {
     familyService.deleteFamilyMember(identity, familyID);
    }

    //通过手机号码添加某一家庭成员,-1表示用户不存在；0表示已存在这个指定的家庭里；1表示加入成功；2表示该用户已存在于别的家庭中；3表示没有这个家庭

    @RequestMapping("/addMember")
    public Integer addFamilyMember(@RequestParam String phoneNumber, @RequestParam Integer familyID) {
      return familyService.addFamilyMember(phoneNumber, familyID);
    }


    //用户发起请求授权,注意identity1是请求授权方，identity2是授权方，如果原来的关系是（未授权）关，那么现在的关系是开，反之亦然;
    //注意默认如果是第一次授权那么默认是把关变成了开，即授权

    @PostMapping("/requestAuth")
    public void requestAuth(@RequestParam String identity1, @RequestParam String identity2) {
      familyService.requestAuth(identity1, identity2);
    }



//    返回指定用户的部分信息：头像、姓名、手机号

    @GetMapping("/userGet")
    public UserVo1 userGet(@RequestParam String identity) {
      return familyService.userGet(identity);
    }


//    查看identity2是否对identity1授权；
//    return 0表示无权限，return 1表示有权限

    @GetMapping("/singleAuthorization")
    public Integer singleAuthorization(@RequestParam String identity1, @RequestParam String identity2) {
     return familyService.singleAuthorization(identity1, identity2);
    }


//    返回和家庭中的每一个成员是否有授权关系,注意：传进来的是授权人
//    注意返回的对应关系列表重元素的形式为：
//    用户：是否被授权,返回0表示无权限，1表示有权限

    @GetMapping("/allRelation")
    public Map<String, Integer> allRelation(@RequestParam String identity) {
      return familyService.allRelation(identity);

    }

    //创建一个家庭

    @PostMapping("/createFamily")
    public void createFamily(@RequestParam String houseHolderIdentity,@RequestParam String familyName){
        familyService.createFamily(houseHolderIdentity,familyName);
    }


    //根据户主的identity删除一个家庭

    @PostMapping("/deleteFamily")
    public void deleteFamily(@RequestParam String houseHolderIdentity){
       familyService.deleteFamily(houseHolderIdentity);
    }


    //实现投资组合和收益情况，有权限的情况下查看

    @GetMapping("/familyportfolio")
    public HomePageVo familyMemberPortfolio(@RequestParam  String identity1, @RequestParam  String identity2){
      return familyService.familyMemberPortfolio(identity1,identity2);
    }

    @GetMapping("/isHouseHolder")
    public boolean isHouseHolder(@RequestParam String houseHolderIdentity) {
        return  familyService.isHouseHolder(houseHolderIdentity);
    }


}

