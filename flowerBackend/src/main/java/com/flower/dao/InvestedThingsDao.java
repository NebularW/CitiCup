package com.flower.dao;

import com.flower.Vo.InvestedThingsVo;
import com.flower.service.entity.InvestedThings;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface InvestedThingsDao {
    void insert(String identity, String fundName, String category, Double value, String timeStamp, Double proportion);

    List<InvestedThings> selectAllValidByIdentity(String identity); //获得identity买入的所有类型的基金

    void deleteByIdentity(String identity); //删除identity买入的所有类型的基金

    List<InvestedThingsVo> findValuesByIdentity(String identity); //返回所有正在投资的基金的各种类型的当前价值 Completed 这里能不能返回一个map，key是类型，value是这个类型的价值

    void updateValueByIdentityAndFundName(String identity, String fundName, Double value); //通过参数，更新对应类型基金的value

    Double amountSum(String identity); //Completed 获得identity买入的所有类型的基金的价值总和
}
