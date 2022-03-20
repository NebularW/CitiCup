package com.flower.service.Impl;

import com.flower.dao.*;
import com.flower.service.HealthService;
import com.flower.service.entity.HealthBasicInfo;
import com.flower.service.entity.HealthDrugInfo;
import com.flower.service.entity.HealthMedicalHistory;
import com.flower.service.entity.HealthSugarLevelBloodPressureHeartRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class HealthServiceImpl implements HealthService {
    @Autowired
    HealthBasicInfoDao healthBasicInfoDao;
    @Autowired
    HealthSugarLevelBloodPressureHeartRateDao healthSugarLevelBloodPressureHeartRateDao;
    @Autowired
    HealthDrugInfoDao healthDrugInfoDao;
    @Autowired
    HealthMedicalHistoryDao healthMedicalHistoryDao;
    @Autowired
    UserDao userDao;



    //增加基础信息,0表示男，1表示女

    @Override
    public void addBasicInfo( String identity, String name,
                              Integer age, Integer sex, String IDCardNumber,
                             Double height,  Double weight) {
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
        }else {
            if(healthBasicInfoDao.findAllByOpenID(identity)!=null){
                System.out.println("已存在记录，请不要重复添加！");
            }
            else {
                HealthBasicInfo healthBasicInfo = new HealthBasicInfo();
                healthBasicInfo.setIdentity(identity);
                healthBasicInfo.setName(name);
                healthBasicInfo.setAge(age);
                healthBasicInfo.setSex(sex);
                healthBasicInfo.setIDCardNumber(IDCardNumber);
                healthBasicInfo.setHeight(height);
                healthBasicInfo.setWeight(weight);
                healthBasicInfoDao.insert(healthBasicInfo);
                System.out.println("Success!");
            }
        }


    }



    //修改基础信息,一定要保证不修改identity,否则所有数据将会出错

    @Override
    public void updateBasicInfo(String identity,String name, Integer age, Integer sex,String IDCardNumber,
                                Double height,Double weight) {
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
        }else {
            if(healthBasicInfoDao.findAllByOpenID(identity)==null){
                System.out.println("不存在记录，请先添加！");
            }
            else {
                HealthBasicInfo healthBasicInfo = new HealthBasicInfo();
                healthBasicInfo.setIdentity(identity);
                healthBasicInfo.setName(name);
                healthBasicInfo.setAge(age);
                healthBasicInfo.setSex(sex);
                healthBasicInfo.setIDCardNumber(IDCardNumber);
                healthBasicInfo.setHeight(height);
                healthBasicInfo.setWeight(weight);
                healthBasicInfoDao.update(healthBasicInfo);
                System.out.println("Success!");
            }
        }


    }
    //查询用户基础信息，返回null就是用户名不正确或者根本没有他的记录

    @Override
    public HealthBasicInfo getBasicInfo(@RequestParam String identity){
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
            return null;
        }
        System.out.println("Success!");
        return healthBasicInfoDao.findAllByOpenID(identity);

    }

    //添加血糖（0），血压（1），心率（2）数据

    @Override
    public void insertBloodInfo( String identity,Integer type, Double level,String timestamp ){
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
        }else{
            HealthSugarLevelBloodPressureHeartRate healthSugarLevelBloodPressureHeartRate=new HealthSugarLevelBloodPressureHeartRate();
            healthSugarLevelBloodPressureHeartRate.setIdentity(identity);
            healthSugarLevelBloodPressureHeartRate.setLevel(level);
            healthSugarLevelBloodPressureHeartRate.setTimestamp(timestamp);
            healthSugarLevelBloodPressureHeartRate.setType(type);
            healthSugarLevelBloodPressureHeartRateDao.insert(healthSugarLevelBloodPressureHeartRate);
            System.out.println("Success!");

        }




    }

    //查询血糖（0），血压（1），心率（2）数据,最多返回3条

    @Override
    public List<HealthSugarLevelBloodPressureHeartRate> getBloodInfo(String identity,Integer type){
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
            return null;
        }
        System.out.println("Success!");
        return healthSugarLevelBloodPressureHeartRateDao.findByTypeAndOpenID(type,identity);



    }


    //添加药物数据

    @Override
    public void addMedicineInfo(String identity,String drugName, String timestamp){
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
        }
        else {
            HealthDrugInfo healthDrugInfo = new HealthDrugInfo();
            healthDrugInfo.setDrugName(drugName);
            healthDrugInfo.setIdentity(identity);
            healthDrugInfo.setTimestamp(timestamp);
            healthDrugInfoDao.insert(healthDrugInfo);
            System.out.println("Success!");
        }
    }

    //查询药物数据

    @Override
    public List<HealthDrugInfo> getMedicineInfo(String identity){
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
            return null;
        }
        System.out.println("Success!");
        return healthDrugInfoDao.findByOpenID(identity);

    }


    //添加病史记录

    @Override
    public void addMedicalHistoryInfo( String identity,String diseaseName,
                                      Boolean hasPerformedOperation,
                                      String announcement,String confirmedTime){

        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
        }
        else{
            HealthMedicalHistory healthMedicalHistory=new HealthMedicalHistory();
            healthMedicalHistory.setAnnouncement(announcement);
            healthMedicalHistory.setConfirmedTime(confirmedTime);
            healthMedicalHistory.setDiseaseName(diseaseName);
            healthMedicalHistory.setHasPerformedOperation(hasPerformedOperation);
            healthMedicalHistory.setIdentity(identity);
            healthMedicalHistoryDao.insert(healthMedicalHistory);
            System.out.println("Success!");
        }


    }

    //查询病史记录

    @Override
    public List<HealthMedicalHistory> getMedicalHistoryInfo(String identity){
        if(userDao.selectByIdentity(identity)==null){
            System.out.println("没有这个用户！");
            return null;
        }
        System.out.println("Success!");
        return healthMedicalHistoryDao.findByOpenID(identity);
    }






}
