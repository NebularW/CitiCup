package com.flower.controller;


import com.flower.service.HealthService;
import com.flower.service.entity.HealthBasicInfo;
import com.flower.service.entity.HealthDrugInfo;
import com.flower.service.entity.HealthMedicalHistory;
import com.flower.service.entity.HealthSugarLevelBloodPressureHeartRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuxi
 */
@RestController
@RequestMapping("/health")
public class HealthRecordController {
    @Autowired
    private HealthService healthService;


   //增加基础信息,0表示男，1表示女

    @PostMapping("/addBasicInfo")
    public void addBasicInfo(@RequestParam String identity,@RequestParam String name,
                                @RequestParam Integer age, @RequestParam Integer sex,@RequestParam String IDCardNumber,
                                @RequestParam Double height,@RequestParam Double weight) {
      healthService.addBasicInfo(identity, name, age, sex, IDCardNumber, height, weight);


    }



    //修改基础信息,一定要保证不修改identity,否则所有数据将会出错

    @PostMapping("/updateBasicInfo")
    public void updateBasicInfo(@RequestParam String identity,@RequestParam String name,
                             @RequestParam Integer age, @RequestParam Integer sex,@RequestParam String IDCardNumber,
                             @RequestParam Double height,@RequestParam Double weight) {
      healthService.updateBasicInfo(identity, name, age, sex, IDCardNumber, height, weight);


    }
    //查询用户基础信息，返回null就是用户名不正确或者根本没有他的记录

    @GetMapping("/getBasicInfo")
    public HealthBasicInfo getBasicInfo(@RequestParam String identity){
       return healthService.getBasicInfo(identity);

    }

    //添加血糖（0），血压（1），心率（2）数据

    @PostMapping("/insertBloodInfo")
    public void insertBloodInfo(@RequestParam String identity,@RequestParam Integer type,
                                @RequestParam Double level,@RequestParam String timestamp ){
        healthService.insertBloodInfo(identity, type, level, timestamp);

    }

   //查询血糖（0），血压（1），心率（2）数据,最多返回3条

    @GetMapping("/getBloodInfo")
    public List<HealthSugarLevelBloodPressureHeartRate> getBloodInfo(@RequestParam String identity,@RequestParam Integer type){

        return healthService.getBloodInfo(identity,type);

    }


    //添加药物数据

    @PostMapping("/addMedicineInfo")
    public void addMedicineInfo(@RequestParam String identity,@RequestParam String drugName,@RequestParam String timestamp){
        healthService.addMedicineInfo(identity, drugName, timestamp);
    }

    //查询药物数据

    @GetMapping("/getMedicineInfo")
    public List<HealthDrugInfo> getMedicineInfo(@RequestParam String identity){
      return healthService.getMedicineInfo(identity);
    }


    //添加病史记录

    @PostMapping("/addMedicalHistoryInfo")
    public void addMedicalHistoryInfo(@RequestParam String identity,@RequestParam String diseaseName,
                                      @RequestParam Boolean hasPerformedOperation,
                                      @RequestParam String announcement,@RequestParam String confirmedTime){

        healthService.addMedicalHistoryInfo(identity, diseaseName, hasPerformedOperation, announcement, confirmedTime);

    }

    //查询病史记录

    @GetMapping("/getMedicalHistoryInfo")
    public List<HealthMedicalHistory> getMedicalHistoryInfo(@RequestParam String identity){
     return healthService.getMedicalHistoryInfo(identity);
    }


}
