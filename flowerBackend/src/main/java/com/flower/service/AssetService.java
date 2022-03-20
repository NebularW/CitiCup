package com.flower.service;

import com.flower.Vo.FinancialAssetVo;
import com.flower.Vo.HistoryBenefitsVo;
import com.flower.Vo.HomePageVo;

import java.util.List;

/**
 * @InterfaceName: AssetService
 * @Description: 用于获取用户收益以及投资组合
 * @Author sky
 * @Date 2022/3/7 21:08
 */
public interface AssetService {
    /**
     * 获取用户在主页的资产情况
     */
    HomePageVo getHomePageInfo(String identity);

    /**
     * 获取用户在理财界面的资产情况
     */
    FinancialAssetVo getFinancialAsset(String identity);

    /**
     * 获取用户在主页的历史收益
     */
    List<HistoryBenefitsVo> getHistoryBenefits(String identity, String timestamp);
}
