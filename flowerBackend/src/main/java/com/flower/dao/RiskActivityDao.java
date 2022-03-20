package com.flower.dao;

import com.flower.service.entity.RiskActivity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RiskActivityDao {

    //Completed
    void insertRiskActivity(String identity, Integer familyID, String timeStamp, Double amount, String description);

    //Completed 更新该用户最新的红色风险交易行为的description
    void updateDescription(String identity, String description);

    //Completed 通过用户的open id获取他所有的风险交易记录
    List<RiskActivity> selectByIdentity(String identity);

    //Completed 能不能获取用户uncompleted的风险交易（用于用户解冻后存入交易并且豁免，有其他方案可以提出）
    RiskActivity selectUncompletedByIdentity(String identity);

    //Completed 通过family id获取该家庭所有成员的风险交易记录(uncompleted)
    List<RiskActivity> selectByFamilyID(Integer familyID); //成员一进入界面查询这个看有没有成员爆红, isCompleted=false的

    //Completed
    void updateCompletedByIdentity(boolean completed, String identity);
}
