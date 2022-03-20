package com.flower.controller;

import com.flower.Vo.DealVo;
import com.flower.Vo.DescriptionVo;
import com.flower.service.entity.InvestedThings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: InvestControllerTest
 * @Author sky
 * @Date 2022/3/9 18:40
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class InvestControllerTest {
    @Autowired
    InvestController investController;
    String identity = "o_P505bhRowUXLeM9kR7E32sF_54";
    List<InvestedThings> investedThingsList=new ArrayList<>();
    InvestedThings investedThing1 = new InvestedThings();
    InvestedThings investedThing2 = new InvestedThings();
    InvestedThings investedThing3 = new InvestedThings();
    InvestedThings investedThing4 = new InvestedThings();
    InvestedThings investedThing5 = new InvestedThings();
    InvestedThings investedThing6 = new InvestedThings();
    InvestedThings investedThing7 = new InvestedThings();
    InvestedThings investedThing8 = new InvestedThings();
    InvestedThings investedThing9 = new InvestedThings();
    InvestedThings investedThing10 = new InvestedThings();


    public void createTestCase(){
        investedThing1.setIdentity(identity);
        investedThing2.setIdentity(identity);
        investedThing3.setIdentity(identity);
        investedThing4.setIdentity(identity);
        investedThing5.setIdentity(identity);
        investedThing1.setFundName("富国中证煤炭A");
        investedThing2.setFundName("嘉实原油");
        investedThing3.setFundName("工银瑞信生态环境");
        investedThing4.setFundName("华安创业板50ETF");
        investedThing5.setFundName("万家上证50ETF");
        investedThing1.setCategory("股票");
        investedThing2.setCategory("股票");
        investedThing3.setCategory("股票");
        investedThing4.setCategory("股票");
        investedThing5.setCategory("股票");
        investedThing1.setValue(5000.00);
        investedThing2.setValue(3000.00);
        investedThing3.setValue(1000.00);
        investedThing4.setValue(500.00);
        investedThing5.setValue(500.00);
        investedThing1.setProportion(0.5);
        investedThing2.setProportion(0.3);
        investedThing3.setProportion(0.1);
        investedThing4.setProportion(0.05);
        investedThing5.setProportion(0.05);
        investedThingsList.add(investedThing1);
        investedThingsList.add(investedThing2);
        investedThingsList.add(investedThing3);
        investedThingsList.add(investedThing4);
        investedThingsList.add(investedThing5);

        investedThing6.setIdentity(identity);
        investedThing7.setIdentity(identity);
        investedThing8.setIdentity(identity);
        investedThing9.setIdentity(identity);
        investedThing10.setIdentity(identity);
        investedThing6.setFundName("南方金利A");
        investedThing7.setFundName("泰信增强收益A");
        investedThing8.setFundName("华安日日鑫货币H");
        investedThing9.setFundName("海富通上证城投债ETF");
        investedThing10.setFundName("国泰上证5年期国债ETF");
        investedThing6.setCategory("债券");
        investedThing7.setCategory("债券");
        investedThing8.setCategory("债券");
        investedThing9.setCategory("债券");
        investedThing10.setCategory("债券");
        investedThing6.setValue(10000.00);
        investedThing7.setValue(6000.00);
        investedThing8.setValue(2000.00);
        investedThing9.setValue(1000.00);
        investedThing10.setValue(1000.00);
        investedThing6.setProportion(0.5);
        investedThing7.setProportion(0.3);
        investedThing8.setProportion(0.1);
        investedThing9.setProportion(0.05);
        investedThing10.setProportion(0.05);
//        investedThingsList.add(investedThing6);
//        investedThingsList.add(investedThing7);
//        investedThingsList.add(investedThing8);
//        investedThingsList.add(investedThing9);
//        investedThingsList.add(investedThing10);

    }

    //测试正常情况买入基金组合
    @Test
    public void buyFundTest1(){
        System.out.println("buyFund测试1开始");
        createTestCase();
        for(InvestedThings invest:investedThingsList){
            String res = investController.buyFund(invest);
            Assertions.assertEquals("Success!", res);
            System.out.println("成功买入基金：");
            System.out.println(invest);
        }

        System.out.println("buyFund测试1结束");
    }

    //测试买入基金为null的情况
    @Test
    public void buyFundTest2(){
        System.out.println("buyFund测试2开始");
        InvestedThings invest_null = new InvestedThings();
//        String res = investController.buyFund(invest_null);
//        Assertions.assertEquals("Success!", res);
        System.out.println("成功买入基金：");
        System.out.println(invest_null);
        System.out.println("buyFund测试2结束");
    }

    //测试买入基金信息中identity为null的情况
    @Test
    public void buyFundTest3(){
        System.out.println("buyFund测试3开始");
        InvestedThings investedThings_null = new InvestedThings();
        investedThings_null.setIdentity(null);

//        String res = investController.buyFund(investedThings_null);
//        Assertions.assertEquals("Success!", res);
        System.out.println("成功买入基金：");
        System.out.println(investedThings_null);
        System.out.println("buyFund测试3结束");
    }

    //测试正常追加投资情况
    @Test
    public void addAmountTest1(){
        DealVo dealVo = new DealVo();
        dealVo.setIdentity(identity);
        dealVo.setAmount(20000.00);
        String res = investController.addAmount(dealVo);
        Assertions.assertEquals("Success!", res);
    }

    //测试追加投资金额为负的情况
    @Test
    public void addAmountTest2(){
        DealVo dealVo = new DealVo();
        dealVo.setIdentity(identity);
        dealVo.setAmount(-20000.00);
        String res = investController.addAmount(dealVo);
        Assertions.assertEquals("Success!", res);
    }


    //测试更改投资组合
    @Test
    public void changePortfolioTest(){
        createTestCase();
        String res = investController.changePortfolio(investedThingsList);
        Assertions.assertEquals("Success!", res);
    }

    //测试卖出基金（不触发红色风险）
    @Test
    public void sellFundTest1(){
        DealVo dealVo = new DealVo();
        dealVo.setIdentity(identity);
        dealVo.setAmount(20000.00);
        String res = investController.sellFund(dealVo);
        Assertions.assertEquals("Red Risk", res);
    }

//    测试卖出基金（两次额度相同）
    @Test
    public void sellFundTest2(){
        DealVo dealVo = new DealVo();
        dealVo.setIdentity(identity);
        dealVo.setAmount(10.00);
        String res = investController.sellFund(dealVo);
//        Assertions.assertEquals("确定再次支付吗", res);
    }

    //测试卖出基金（触发红色风险）
    @Test
    public void sellFundTest3(){
        DealVo dealVo = new DealVo();
        dealVo.setIdentity(identity);
        dealVo.setAmount(10000.00);
        String res = investController.sellFund(dealVo);
        Assertions.assertEquals("Red Risk", res);
    }


    //测试卖出基金（金额超过总资产）
    @Test
    public void sellFundTest4(){
        DealVo dealVo = new DealVo();
        dealVo.setIdentity(identity);
        dealVo.setAmount(50000.00);
        String res = investController.sellFund(dealVo);
        Assertions.assertEquals("Red Risk", res);
    }



    //测试风险说明（正常情况）
    @Test
    public void descriptionRiskTest1(){
        DescriptionVo descriptionVo = new DescriptionVo();
        descriptionVo.setIdentity("o_P505bhRowUXLeM9kR7E32sF_54");
        descriptionVo.setDescription("超过用户限额");
        String res = investController.describeRisk(descriptionVo);
        Assertions.assertEquals("Success!", res);
    }

    //测试风险说明（null）
    @Test
    public void descriptionRiskTest2(){
        DescriptionVo descriptionVo = new DescriptionVo();
        descriptionVo.setIdentity("o_P505VQk1enxptHjOTx3NMkHQZM");
        descriptionVo.setDescription(null);
        String res = investController.describeRisk(descriptionVo);
        Assertions.assertEquals("Description cannot be null!", res);
    }



}
