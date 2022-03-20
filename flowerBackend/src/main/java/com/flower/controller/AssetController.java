package com.flower.controller;

import com.flower.Vo.FinancialAssetVo;
import com.flower.Vo.HistoryAssetVo;
import com.flower.Vo.HistoryBenefitsVo;
import com.flower.Vo.HomePageVo;
import com.flower.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: AssetController
 * @Description: 查看用户资产及收益
 * @Author: sky
 * @Date: 2022/3/5 22:26
 **/
@RestController
@RequestMapping("/asset")
public class AssetController {
    @Autowired
    private AssetService assetService;

    @RequestMapping("/homepage")
    public HomePageVo getHomePageInfo(@RequestParam String identity){
        return assetService.getHomePageInfo(identity);
    }

    @RequestMapping("/history")
    public List<HistoryBenefitsVo> getHistoryBenefits(@RequestBody HistoryAssetVo historyAssetVo){
        return assetService.getHistoryBenefits(historyAssetVo.getIdentity(), historyAssetVo.getTimestamp());
    }

    @RequestMapping("/financial")
    public FinancialAssetVo getFinancialAsset(@RequestParam String identity){
        return assetService.getFinancialAsset(identity);
    }
}
