package com.flower.service;

import com.flower.Vo.FamilyRiskVo;
import com.flower.Vo.RiskToBeProcessedVo;
import com.flower.service.entity.ResponseAction;

import java.util.List;

/**
 * @InterfaceName: RiskService
 * @Author: sky
 * @Date: 2022/2/25 1:00
 **/
public interface RiskService {
    /**
     * 获取家庭成员的风险等级及所有风险交易记录
     */
    List<FamilyRiskVo> familyRisk(String identity);

    /**
     * 给出账户风险信息及需要做出的操作
     */
    RiskToBeProcessedVo riskToPro(String identity);

    /**
     * 返回对被冻结家庭成员风险操作的回应
     */
    String responseToRisk(List<ResponseAction> responseActions);

    /**
     * 更新用户交易频率上限和额度上限
     */
    String updateFrequencyAndLimit(String identity,Integer frequency,Double limit);

    void thaw(String identity);
}
