package com.flower.dao;

import com.flower.Vo.*;
import com.flower.service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    void addUser(RegisterVo registerVo) throws DuplicateKeyException;//Completed 添加新用户，添加信息见LoginVo类

    void updatePhoneNumberByIdentity(String identity, String phoneNumber); //设置中修改手机号

    void updateFamilyIDByIdentity(String identity, Integer familyID); //添加新成员到家庭

    void updateRelationshipToFamilyHolderByIdentity(String identity, String relationshipToFamilyHolder); // 更新成员相对于户主的关系

    void updateFontSizeByIdentity(String identity, Integer fontSize); //长辈协助后可以通过这个修改长辈的字体

    void updateAppStringByIdentity(String identity, String appString); //长辈协助后可以通过这个修改长辈的应用

    void updateRiskLevelByIdentity(Integer riskLevel, String identity);

    void updateAssetsByIdentity(String identity, Double assets); //更新投资组合的时候改变资产为零

    void updateFrequencyByIdentity(String identity, Integer frequency);

    void updateLimitByIdentity(String identity, Double limit);

    void updateModeByIdentity(String identity, Integer mode); //修改模式, 0表示青年模式, 1表示老年模式

    User selectByIdentity(String identity);

    String findIdentityByPhoneNumber(String phoneNumber); //通过电话找到openid

    List<String> selectByFamilyId(Integer familyID); //通过familyID查询所有家庭成员的identity

    User selectByPhoneNumber(String phoneNumber);

    UserVo1 selectById_1(String identity);

    UserVo2 selectById_2(String identity);

    //返回所有的成员 TODO wfs 修改相应需求文件、体系结构文件、dao.xml
    List<User> selectAllUser();
}