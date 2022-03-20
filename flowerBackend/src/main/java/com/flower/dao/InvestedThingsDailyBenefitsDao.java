package com.flower.dao;

import com.flower.service.entity.InvestedThingsDailyBenefits;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InvestedThingsDailyBenefitsDao {
    void insert(String ownerIdentity, String fundName, String category, String timeStamp, double benefit); //新增一条记录

    List<Double> findBenefitByOwnerIdentityAndTimeStamp(String ownerIdentity, String timeStamp); //昨日收益

    Double findBenefitsYesterdaySumByOwnerIdentityAndTimeStamp(String ownerIdentity, String timeStamp); //Completed 获取昨天的收益之和, timeStamp是今天的时间

    Double findBenefitsSumByOwnerIdentityAndTimeStamp(String ownerIdentity); //Completed 获取用户投资以来收益之和

    Double findBenefitsDaySumByOwnerIdentityAndTimeStamp(String ownerIdentity, String timeStamp); //Completed 获取用户某天的收益之和

    List<InvestedThingsDailyBenefits> findAllByIdentity(String identity); //收益情况曲线

    List<InvestedThingsDailyBenefits> findByIdentityAndTimestamp(String identity, String timeStamp); //历史记录中的上半页面 Completed 需要的不是按类型分，而是按基金名
}
