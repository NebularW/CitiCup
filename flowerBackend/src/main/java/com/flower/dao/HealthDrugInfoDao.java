package com.flower.dao;

import com.flower.service.entity.HealthDrugInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthDrugInfoDao {
    void insert(HealthDrugInfo healthDrugInfo);

    List<HealthDrugInfo> findByOpenID(String identity); //返回最新3条记录
}
