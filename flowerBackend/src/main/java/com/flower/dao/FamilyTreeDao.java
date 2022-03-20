package com.flower.dao;

import com.flower.service.entity.FamilyTree;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FamilyTreeDao {
    //插入成功返回familyID
    void insertFamilyTree(String houseHolderIdentity, String familyName);

    void updateMemberNumberByFamilyID(Integer id, Integer memberNumber);

    //Completed
    void updateRedLevelNumberByFamilyID(Integer id, Integer redLevelNumber);

    //Completed 通过家庭ID获取这个家庭中红色风险的家庭成员人数
    Integer getRedLevelNumberByFamilyID(Integer id);

    FamilyTree selectByFamilyID(Integer id);

    FamilyTree selectByHouseHolderIdentity(String houseHolderIdentity);

    Integer findCreditByFamilyID(Integer familyID); //家庭积分

    void updateCreditByFamilyID(Integer familyID, Integer credit); //更新家庭积分

    String findFamilyNameByFamilyID(Integer familyID); //家庭名称

    void updateFamilyNameByFamilyID(Integer familyID, String familyName); //更新家庭名称

    Integer findMemberNumberByFamilyID(Integer familyID); //获得家庭成员个数


    void updateFamilyMember(Integer familyID,Integer memberNumber);

    //删除一个家庭

    void deleteFamilyTree(String houseHolderIdentity);

}
