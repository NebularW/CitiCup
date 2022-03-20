package com.flower.controller;

import com.flower.Vo.FinancialAssetVo;
import com.flower.Vo.HistoryAssetVo;
import com.flower.Vo.HistoryBenefitsVo;
import com.flower.Vo.HomePageVo;
import com.flower.service.entity.InvestedThings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @ClassName: AssetControllerTest
 * @Author: sky
 * @Date: 2022/3/9 22:23
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class AssetControllerTest {
    @Autowired
    AssetController assetController;
    String identity="o_P505VQk1enxptHjOTx3NMkHQZM";
    HistoryAssetVo historyAssetVo = new HistoryAssetVo();

    @Test
    public void getHomePageInfoTest1(){
        System.out.println("getHomePageInfo测试开始");
        HomePageVo homePageInfo = assetController.getHomePageInfo(identity);
        System.out.println("以下是主页信息：");
        System.out.println("我的资产："+homePageInfo.getAssets());
        System.out.println("昨日收益："+homePageInfo.getBenefitsYesterday());
        System.out.println("收益曲线"+homePageInfo.getBenefitsCurve());
        System.out.println("投资组合："+homePageInfo.getInvestedThingsList());
        System.out.println("getHomePageInfo测试结束");
    }

    @Test
    public void getHomePageInfoTest2(){
        System.out.println("getHomePageInfo_null测试开始");
        HomePageVo homePageInfo = assetController.getHomePageInfo(null);
        System.out.println("以下是主页信息：");
        System.out.println("我的资产："+homePageInfo.getAssets());
        System.out.println("昨日收益："+homePageInfo.getBenefitsYesterday());
        System.out.println("收益曲线"+homePageInfo.getBenefitsCurve());
        System.out.println("投资组合："+homePageInfo.getInvestedThingsList());
        System.out.println("getHomePageInfo_null测试结束");
    }

    @Test
    public void getHistoryBenefitsTest1(){
        System.out.println("getHistoryBenefits测试开始");
        historyAssetVo.setIdentity(identity);
        historyAssetVo.setTimestamp("2022-03-18");
        List<HistoryBenefitsVo> historyBenefitsVoList = assetController.getHistoryBenefits(historyAssetVo);
        for(HistoryBenefitsVo historyBenefitsVo:historyBenefitsVoList) System.out.println(historyBenefitsVo);
        System.out.println("getHistoryBenefits测试结束");
    }

    @Test
    public void getHistoryBenefitsTest2(){
        System.out.println("getHistoryBenefits_null测试开始");
        historyAssetVo.setIdentity(null);
        historyAssetVo.setTimestamp("2022-03-15");
        List<HistoryBenefitsVo> historyBenefitsVoList = assetController.getHistoryBenefits(historyAssetVo);
        System.out.println(historyBenefitsVoList);
        System.out.println("getHistoryBenefits_null测试结束");
    }

    @Test
    public void getFinancialAssetTest1(){
        System.out.println("getFinancialAsset测试开始");
        FinancialAssetVo financialAssetVo = assetController.getFinancialAsset(identity);
        System.out.println("用户总资产："+financialAssetVo.getAssets());
        System.out.println("昨日收益："+financialAssetVo.getBenefitsYesterday());
        System.out.println("累计收益："+financialAssetVo.getBenefitsSum());
        System.out.println("用户当前投资组合：");
        for(InvestedThings investedThings:financialAssetVo.getInvestedThingsList()) System.out.println(investedThings);
        System.out.println("getFinancialAsset测试结束");
    }

    @Test
    public void getFinancialAssetTest2(){
        System.out.println("getFinancialAsset_null测试开始");
        FinancialAssetVo financialAssetVo = assetController.getFinancialAsset(null);
        System.out.println("用户总资产："+financialAssetVo.getAssets());
        System.out.println("昨日收益："+financialAssetVo.getBenefitsYesterday());
        System.out.println("累计收益："+financialAssetVo.getBenefitsSum());
        System.out.println("用户当前投资组合：");
        for(InvestedThings investedThings:financialAssetVo.getInvestedThingsList()) System.out.println(investedThings);
        System.out.println("getFinancialAsset_null测试结束");
    }


}
