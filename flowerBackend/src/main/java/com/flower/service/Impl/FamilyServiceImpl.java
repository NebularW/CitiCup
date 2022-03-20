package com.flower.service.Impl;

import com.flower.Vo.HomePageVo;
import com.flower.Vo.UserVo1;
import com.flower.Vo.UserVo2;
import com.flower.dao.AuthorizationForMemberCardDao;
import com.flower.dao.FamilyTreeDao;
import com.flower.dao.InvestedThingsRecordsDao;
import com.flower.dao.UserDao;
import com.flower.service.AssetService;
import com.flower.service.FamilyService;
import com.flower.service.entity.AuthorizationForMemberCard;
import com.flower.service.entity.FamilyTree;
import com.flower.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    UserDao userDao;
    @Autowired
    FamilyTreeDao familyTreeDao;
    @Autowired
    AuthorizationForMemberCardDao authorizationForMemberCardDao;
    @Autowired
    InvestedThingsRecordsDao investedThingsRecordsDao;
    @Autowired
    AssetService assetService;

    //判断该用户是否在某个家庭里,0表示不存在这个用户或者说这个用户不再某个家庭里,1表示在

    @Override
    public Integer inFamily(String identity) {
        User user = userDao.selectByIdentity(identity);
        if(user==null||user.getFamilyID()==null){
            return 0;
        }
        return 1;
    }


    //返回该用户的familyID,-1表示该用户不存在，或者说存在但不在某一个家庭里

    @Override
    public Integer getFamilyID(String identity) {
        if(userDao.selectByIdentity(identity)==null||userDao.selectByIdentity(identity).getFamilyID()==null){
            return -1;
        }
        System.out.println("Success!");
        return userDao.selectByIdentity(identity).getFamilyID();
    }


    //返回该用户所在家庭的所有成员的openid/identity

    @Override
    public List<String> getUsers(String identity) {
        User user = userDao.selectByIdentity(identity);
        if(user==null||user.getFamilyID()==null){
            return null;
        }
        Integer familyid = user.getFamilyID();
        System.out.println("Success!");

        return userDao.selectByFamilyId(familyid);
    }

//    返回家庭中所有成员的identity,头像和姓名

    @Override
    public List<UserVo2> getAllMembers(String identity) {
        if(userDao.selectByIdentity(identity)==null){
            return null;
        }
        List<String> relation = getUsers(identity);
        if(relation==null){
            return null;
        }
        List<UserVo2> result = new ArrayList<>();
        for (String str : relation) {
            UserVo2 temp =userDao.selectById_2(str);
            result.add(temp);
        }
        System.out.println("Success!");
        return result;

    }

    //删除某一家庭成员

    @Override
    public void deleteFamilyMember(String identity, Integer familyID) {
        User user = userDao.selectByIdentity(identity);
        if(user==null){
            System.out.println("该用户不存在！");
        }
        else {
            FamilyTree familyTree = familyTreeDao.selectByFamilyID(familyID);
            if(familyTree==null){
                System.out.println("家庭不存在！");
            }
            else {
                if(familyTree.getHouseHolderIdentity().equals(identity)){
                    deleteFamily(identity);
                    System.out.println("Success!");
                }else {
                    userDao.updateFamilyIDByIdentity(identity, null);
                    familyTreeDao.updateFamilyMember(familyID, familyTreeDao.findMemberNumberByFamilyID(familyID) - 1);
                    System.out.println("Success!");
                }
            }
        }
    }

    //通过手机号码添加某一家庭成员,-1表示用户不存在；0表示已存在这个指定的家庭里；1表示加入成功；2表示该用户已存在于别的家庭中；3表示没有这个家庭

    @Override
    public Integer addFamilyMember(String phoneNumber,Integer familyID) {
        String identity=userDao.findIdentityByPhoneNumber(phoneNumber);
        if(userDao.selectByIdentity(identity)==null||identity==null) {
            System.out.println("该用户不存在！");
            return -1;
        }
        else {
            User user = userDao.selectByIdentity(identity);
            if(familyTreeDao.selectByFamilyID(familyID)==null){
                System.out.println("没有这个家庭！");
                return 3;
            }
            if(user.getFamilyID()==null){
                userDao.updateFamilyIDByIdentity(identity,familyID);
                FamilyTree familyTree = familyTreeDao.selectByFamilyID(familyID);
                familyTreeDao.updateFamilyMember(familyID,familyTreeDao.findMemberNumberByFamilyID(familyID)+1);
                return 1;
            }
            else if(user.getFamilyID().equals(familyID)){
                System.out.println("该用户已经存在这个家庭里,不可重复加入！");
                return 0;
            }
            else {
                System.out.println("该用户已存在于别的家庭中");
                return 2;
            }
        }
    }


    //用户发起请求授权,注意identity1是请求授权方，identity2是授权方，如果原来的关系是（未授权）关，那么现在的关系是开，反之亦然;
    //注意默认如果是第一次授权那么默认是把关变成了开，即授权

    @Override
    public void requestAuth(String identity1, String identity2) {
        if(userDao.selectByIdentity(identity1)==null||userDao.selectByIdentity(identity2)==null){
            System.out.println("用户id不正确！");
        }
        else {
            AuthorizationForMemberCard authorizationForMemberCard = authorizationForMemberCardDao.findAllByFromAndTo(identity1, identity2);
            if (authorizationForMemberCard == null) {
                authorizationForMemberCardDao.addAuthorizationRequest(identity1, identity2);
                authorizationForMemberCardDao.updateIsAuthorizedByFromAndTo(identity1,identity2,true);
                System.out.println("Success!");

            } else {
                if (authorizationForMemberCard.isAuthorized()) {
                    authorizationForMemberCardDao.updateIsAuthorizedByFromAndTo(identity1,identity2,false);
                    System.out.println("Success!");
                } else {
                    authorizationForMemberCardDao.updateIsAuthorizedByFromAndTo(identity1,identity2,true);
                    System.out.println("Success!");
                }
            }
        }
    }



//    返回指定用户的部分信息：头像、姓名、手机号

    @Override
    public UserVo1 userGet(String identity) {
        if(userDao.selectById_1(identity)==null){
            return null;
        }
        System.out.println("Success!");
        return userDao.selectById_1(identity);
    }


//    查看identity2是否对identity1授权；
//    return 0表示无权限，return 1表示有权限

    @Override
    public Integer singleAuthorization(String identity1,String identity2) {
        if(userDao.selectByIdentity(identity1)==null||userDao.selectByIdentity(identity2)==null){
            return null;
        }
        AuthorizationForMemberCard authorizationForMemberCard = authorizationForMemberCardDao.findAllByFromAndTo(identity1, identity2);
        if (authorizationForMemberCard == null|| !authorizationForMemberCard.isAuthorized()) {
            return 0;
        } else {
            System.out.println("Success!");
            return 1;
        }
    }


//    返回和家庭中的每一个成员是否有授权关系,注意：传进来的是授权人
//    注意返回的对应关系列表重元素的形式为：
//    用户：是否被授权,返回0表示无权限，1表示有权限

    @Override
    public Map<String, Integer> allRelation(String identity) {
        Map<String, Integer> result = new HashMap<>();
        List<String> relation = getUsers(identity);
        if(relation==null||userDao.selectByIdentity(identity)==null){
            System.out.println("用户不存在！");
            return null;
        }
        else {
            Integer flag;
            for (String str : relation) {
                flag = singleAuthorization(str, identity);
                if (flag == 0) {
                    result.put(str, 0);
                } else {
                    result.put(str, 1);
                }
            }
            System.out.println("Success!");
            return result;
        }

    }

    //创建一个家庭

    @Override
    public void createFamily(String houseHolderIdentity,String familyName){
        if(userDao.selectByIdentity(houseHolderIdentity)==null){
            System.out.println("用户不存在或者id输入有误！");
        }
        else if(familyTreeDao.selectByHouseHolderIdentity(houseHolderIdentity)!=null){
            System.out.println("家庭已存在,不可重复创建!");
        }
        else {
            familyTreeDao.insertFamilyTree(houseHolderIdentity, familyName);
            FamilyTree familyTree=familyTreeDao.selectByHouseHolderIdentity(houseHolderIdentity);
            userDao.updateFamilyIDByIdentity(houseHolderIdentity,familyTree.getId());
            System.out.println("Success!");
        }
    }


    //根据户主的identity删除一个家庭

    @Override
    public void deleteFamily(String houseHolderIdentity){
        if(familyTreeDao.selectByHouseHolderIdentity(houseHolderIdentity)==null||userDao.selectByIdentity(houseHolderIdentity)==null){
            System.out.println("不存在这个家庭或者户主的identity输入有误！");
        }
        else {
            List<String> relation = getUsers(houseHolderIdentity);
            if(relation==null){
                System.out.println("只存在户主！");
                userDao.updateFamilyIDByIdentity(houseHolderIdentity,null);
                familyTreeDao.deleteFamilyTree(houseHolderIdentity);
            }
            else {
                for (String str : relation) {
                    userDao.updateFamilyIDByIdentity(str, null);
                }
                familyTreeDao.deleteFamilyTree(houseHolderIdentity);
                System.out.println("Success!");
            }
        }
    }



    //实现投资组合和收益情况，有权限的情况下查看

    @Override
    public HomePageVo familyMemberPortfolio(String identity1, String identity2){
        if(userDao.selectByIdentity(identity1)==null||userDao.selectByIdentity(identity2)==null){
            System.out.println("用户id输入有误！");
            return null;
        }
        Map<String,Integer> allRelation = allRelation(identity2);
        if(allRelation==null){
            return null;
        }
        if(allRelation.get(identity1)==1) {
            System.out.println("Success!");
            return assetService.getHomePageInfo(identity1);
        }
        return null;
    }

    @Override
    public boolean isHouseHolder(String houseHolderIdentity){
        if(userDao.selectByIdentity(houseHolderIdentity)==null){
            System.out.println("没有该用户！");
            return false;
        }
        else{
            System.out.println("Success!");
            return familyTreeDao.selectByHouseHolderIdentity(houseHolderIdentity) != null;
        }
    }

}
