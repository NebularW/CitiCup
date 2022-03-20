package com.flower.dao;

import com.flower.service.entity.HealthMedicalHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthMedicalHistoryDao {
    void insert(HealthMedicalHistory healthMedicalHistory);

    List<HealthMedicalHistory> findByOpenID(String identity); //返回最新三条记录
}
