package com.flower.service.Impl;

import com.flower.Vo.InvestedThingsVo;
import com.flower.Vo.RegisterVo;
import com.flower.dao.*;
import com.flower.service.entity.HealthBasicInfo;
import com.flower.service.entity.HealthDrugInfo;
import com.flower.service.entity.HealthMedicalHistory;
import com.flower.service.entity.HealthSugarLevelBloodPressureHeartRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 这个类用于测试Dao接口是否正确
 * @Author: wfs
 */
@Service
public class TestForDBService {
    @Autowired
    private InvestedThingsDao investedThingsDao;

    public List<InvestedThingsVo> find1(String identity){
        return investedThingsDao.findValuesByIdentity(identity);
    }

    public Double find2(String identity){
        return investedThingsDao.amountSum(identity);
    }

    @Autowired
    private InvestedThingsDailyBenefitsDao investedThingsDailyBenefitsDao;

    public Double find3(){
        return investedThingsDailyBenefitsDao.findBenefitsYesterdaySumByOwnerIdentityAndTimeStamp("1", "2022-3-7");
    }

    public Double find4(){
        return investedThingsDailyBenefitsDao.findBenefitsSumByOwnerIdentityAndTimeStamp("1");
    }

    public Double find5(String ownerIdentity, String timeStamp){
        return investedThingsDailyBenefitsDao.findBenefitsDaySumByOwnerIdentityAndTimeStamp(ownerIdentity, timeStamp);
    }

    @Autowired
    private UserDao userDao;

    public void insert(RegisterVo registerVo){
        userDao.addUser(registerVo);
    }


    @Autowired
    private ResponseActionDao responseActionDao;

    public boolean find6(String toIdentity){
        return responseActionDao.isRefused(toIdentity);
    }

    @Autowired
    private AuthorizationForMemberCardDao authorizationForMemberCardDao;

    public void insert(String fromIdentity, String toIdentity){
        authorizationForMemberCardDao.addAuthorizationRequest(fromIdentity, toIdentity);
    }

    @Autowired
    private FamilyTreeDao familyTreeDao;

    public void insert2(String houseHolderIdentity, String familyName){
        familyTreeDao.insertFamilyTree(houseHolderIdentity, familyName);
    }

    @Autowired
    private HealthBasicInfoDao healthBasicInfoDao;

    public void insert3(HealthBasicInfo healthBasicInfo){
        healthBasicInfoDao.insert(healthBasicInfo);
    }

    public HealthBasicInfo find7(String identity) {
        return healthBasicInfoDao.findAllByOpenID(identity);
    }

    public void update1(HealthBasicInfo healthBasicInfo) {
        healthBasicInfoDao.update(healthBasicInfo);
    }

    @Autowired
    private HealthDrugInfoDao healthDrugInfoDao;

    public void insert4(HealthDrugInfo healthDrugInfo) {
        healthDrugInfoDao.insert(healthDrugInfo);
    }

    public List<HealthDrugInfo> find8(String identity) {
        return healthDrugInfoDao.findByOpenID(identity);
    }

    @Autowired
    private HealthMedicalHistoryDao healthMedicalHistoryDao;

    public void insert5(HealthMedicalHistory healthMedicalHistory) {
        healthMedicalHistoryDao.insert(healthMedicalHistory);
    }

    public List<HealthMedicalHistory> find9(String identity) {
        return healthMedicalHistoryDao.findByOpenID(identity);
    }

    @Autowired
    private HealthSugarLevelBloodPressureHeartRateDao healthSugarLevelBloodPressureHeartRateDao;

    public void insert6(HealthSugarLevelBloodPressureHeartRate healthSugarLevelBloodPressureHeartRate) {
        healthSugarLevelBloodPressureHeartRateDao.insert(healthSugarLevelBloodPressureHeartRate);
    }

    public List<HealthSugarLevelBloodPressureHeartRate> find10(String identity, Integer type) {
        return healthSugarLevelBloodPressureHeartRateDao.findByTypeAndOpenID(type, identity);
    }
}
