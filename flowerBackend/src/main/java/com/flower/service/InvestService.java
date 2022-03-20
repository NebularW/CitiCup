package com.flower.service;



import com.flower.service.entity.InvestedThings;

import java.util.List;

/**
 * @interfaceName: InvestService
 * @Author: sky
 * @Date: 2022/2/24 21:13
 **/
public interface InvestService {
    /**
     * 完成本笔买入交易（以基金为单位）
     */
    void buyFund(InvestedThings invest);

    /**
     * 追加投资（以投资组合为单位）
     */
    void addAmount(String identity, Double amount, String timestamp);


    /**
     * 更改投资组合
     */
    void changePortfolio(List<InvestedThings> investedThingsList);

    /**
     * 分析本笔卖出交易风险（以基金为单位）
     * 若风险为绿色或者黄色，则按比例卖出基金
     */
    String sellFund(String identity, Double amount, boolean confirmed);


    /**
     * 更新用户关于该风险交易的描述
     */
    String updateDescription(String identity, String description);


}
