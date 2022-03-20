package com.flower.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName: SellingRecordDao
 * @Description:
 * @Author: sky
 * @Date: 2022/3/6 14:25
 **/
@Mapper
@Repository
public interface SellingRecordDao {
    //Completed  插入用户卖出操作（投资组合为单位）
    void insertSellActivity(String identity, String timestamp, Double amount, boolean isExempted);

    //Completed 获取用户30天内卖出的次数
    Integer frequencyIn30Days(String identity, String timestamp);

    //Completed 获取用户30天内卖出的金额
    Double amountIn30Days(String identity, String timestamp);

    //Completed 获取用户最新一笔卖出交易的金额
    Double lastAmount(String identity);

    //TODO 插入用户升为黄色风险的记录
    void InsertYellowRisk(String identity, String timestamp);

    //TODO 用户是否在30天内有过黄色风险
    boolean isYellowIn30Days(String identity,String timestamp);

    //Completed 用户最近0-30天、31-60天、61-90天内的卖出交易总额
    List<Double> amountIn90Days(String identity);

}
