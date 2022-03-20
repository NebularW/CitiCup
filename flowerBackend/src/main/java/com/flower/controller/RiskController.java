package com.flower.controller;

import com.flower.Vo.FamilyRiskVo;
import com.flower.Vo.FrequencyAndLimitVo;
import com.flower.Vo.RiskToBeProcessedVo;
import com.flower.service.entity.ResponseAction;
import com.flower.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RiskController
 * @Author: sky
 * @Date: 2022/2/25 0:59
 **/
@RestController
@RequestMapping("/risk")
public class RiskController {
    @Autowired
    private RiskService riskService;

    //设置用户交易频率上限和额度上限
    @RequestMapping("upper")
    public String updateFrequencyAndLimit(@RequestParam FrequencyAndLimitVo frequencyAndLimitVo){
        return riskService.updateFrequencyAndLimit(frequencyAndLimitVo.getIdentity(),frequencyAndLimitVo.getFrequency(),frequencyAndLimitVo.getLimit());
    }

    //获取家庭成员的风险等级及所有风险交易记录
    @RequestMapping("/family")
    public List<FamilyRiskVo> familyRisk(@RequestParam String identity){
        return riskService.familyRisk(identity);
    }

    //给出用户及家人的风险信息以及是否需要做出操作
    @RequestMapping("/process")
    public RiskToBeProcessedVo riskToBeProcessed(@RequestParam String identity){
        return riskService.riskToPro(identity);
    }

    //返回对被冻结家庭成员风险操作的回应
    @PostMapping("/response")
    public String responseToRisk(@RequestBody ResponseAction responseAction){
        List<ResponseAction> responseActionList = new ArrayList<>();
        responseActionList.add(responseAction);
        return riskService.responseToRisk(responseActionList);
    }

}
