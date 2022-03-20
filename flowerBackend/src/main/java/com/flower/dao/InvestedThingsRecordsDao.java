package com.flower.dao;

import com.flower.service.entity.InvestedThingsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InvestedThingsRecordsDao {
    List<InvestedThingsRecord> selectByIdentity(String identity);

    /**
     * 插入的时候status默认为0，即买入
     */
    void insertInvestedThingsRecord(String identity, String fundName, String category,
                                    Double amount, String timeStamp, boolean isExempted, Integer status);

    void updateIsExemptedByIdentityAndTimeStamp(boolean isExempted, String identity, String timeStamp);

    List<InvestedThingsRecord> findByTimestampIdentityAndCategory(String timeStamp, String identity, String category); //查询历史记录中基金变动情况

    List<InvestedThingsRecord> findByTimestampAndIdentity(String timeStamp, String identity); //查询历史记录中基金变动情况

    List<InvestedThingsRecord> findByCategoryAndIdentity(String category, String identity); //查询历史记录中基金变动情况
}
