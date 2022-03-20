package com.flower.dao;

import com.flower.service.entity.HealthBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HealthBasicInfoDao {
    void insert(HealthBasicInfo healthBasicInfo);

    HealthBasicInfo findAllByOpenID(String identity);

    void update(HealthBasicInfo healthBasicInfo);
}
