package com.flower.dao;

import com.flower.service.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionDao {
    void insert(Integer id, String description, Integer searchTimes, String type); //插入一条新条目

    List<Question> findByType(String type); //通过问答类型返回内容

    void updateSearchTimesById(Integer id, Integer searchTimes); //修改该条目被搜索次数

    Integer findSearchTimesById(Integer id); //查询该条目被搜索次数

    List<Question> findTopN(Integer N); //返回前N个回答，按照搜索次数降序
}
