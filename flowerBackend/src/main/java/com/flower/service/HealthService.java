package com.flower.service;

import com.flower.service.entity.HealthBasicInfo;
import com.flower.service.entity.HealthDrugInfo;
import com.flower.service.entity.HealthMedicalHistory;
import com.flower.service.entity.HealthSugarLevelBloodPressureHeartRate;


import java.util.List;

public interface HealthService {



     void addBasicInfo(String identity, String name, Integer age,  Integer sex, String IDCardNumber, Double height, Double weight);



     void updateBasicInfo( String identity,String name,Integer age, Integer sex,String IDCardNumber,Double height,Double weight);


     HealthBasicInfo getBasicInfo(String identity);



    void insertBloodInfo(String identity,Integer type, Double level,String timestamp );



    List<HealthSugarLevelBloodPressureHeartRate> getBloodInfo(String identity, Integer type);


    void addMedicineInfo(String identity,String drugName,String timestamp);




    List<HealthDrugInfo> getMedicineInfo(String identity);



    void addMedicalHistoryInfo(String identity,String diseaseName, Boolean hasPerformedOperation, String announcement,String confirmedTime);




     List<HealthMedicalHistory> getMedicalHistoryInfo(String identity);





}
