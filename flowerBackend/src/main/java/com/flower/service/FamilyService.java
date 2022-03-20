package com.flower.service;

import com.flower.Vo.HomePageVo;
import com.flower.Vo.UserVo1;
import com.flower.Vo.UserVo2;

import java.util.List;
import java.util.Map;

public interface FamilyService {


    Integer inFamily(String identity);


    Integer getFamilyID(String identity);


    List<String> getUsers( String identity);


    List<UserVo2> getAllMembers(String identity);


    void deleteFamilyMember(String identity, Integer familyID);

    Integer addFamilyMember(String phoneNumber, Integer familyID);


    void requestAuth(String identity1, String identity2);


    UserVo1 userGet(String identity);


    Integer singleAuthorization(String identity1, String identity2);


    Map<String, Integer> allRelation(String identity);


    void createFamily(String houseHolderIdentity,String familyName);


    void deleteFamily(String houseHolderIdentity);


    HomePageVo familyMemberPortfolio(String identity1, String identity2);


    boolean isHouseHolder(String houseHolderIdentity);


}
