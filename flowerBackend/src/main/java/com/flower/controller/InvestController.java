package com.flower.controller;


import com.flower.Vo.DealVo;
import com.flower.Vo.DescriptionVo;
import com.flower.service.Impl.ModelServiceImpl;
import com.flower.service.InvestService;
import com.flower.service.entity.InvestedThings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName: InvestController
 * @Author: sky
 * @Description: 用于买入、追加投资、卖出以及陷入红色风险后用户的描述
 * @Date: 2022/2/24 21:25
 **/
@RestController
@RequestMapping("/invest")
public class InvestController {

    @Autowired
    private InvestService investService;
    @Autowired
    private ModelServiceImpl modelServiceImpl;

    @PostMapping("/buy")
    public String buyFund(@RequestBody InvestedThings invest) {
        invest.setTimeStamp(String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10));
        investService.buyFund(invest);
        return "Success!";
    }

    @PostMapping("/add")
    public String addAmount(@RequestBody DealVo dealVo) {
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10);
        investService.addAmount(dealVo.getIdentity(), dealVo.getAmount(), timestamp);
        return "Success!";
    }

    @PostMapping("change")
    public String changePortfolio(@RequestParam List<InvestedThings> investedThingsList) {
        investService.changePortfolio(investedThingsList);
        return "Success!";
    }

    @PostMapping("/sell")
    public String sellFund(@RequestBody DealVo dealVo) {
        return investService.sellFund(dealVo.getIdentity(), dealVo.getAmount(), dealVo.isConfirmed());
    }

    @PostMapping("/description")
    public String describeRisk(@RequestParam DescriptionVo descriptionVo) {
        return investService.updateDescription(descriptionVo.getIdentity(), descriptionVo.getDescription());
    }




}
