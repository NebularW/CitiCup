package com.flower.dao;

import com.flower.service.entity.HealthSugarLevelBloodPressureHeartRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthSugarLevelBloodPressureHeartRateDao {
    void insert(HealthSugarLevelBloodPressureHeartRate healthSugarLevelBloodPressureHeartRate);

    List<HealthSugarLevelBloodPressureHeartRate> findByTypeAndOpenID(Integer type, String identity); //返回最新的3条数据
}
