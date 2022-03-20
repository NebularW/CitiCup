package com.flower.service.Impl;

import com.flower.dao.*;
import com.flower.service.entity.InvestedThings;
import com.flower.service.entity.User;
import com.flower.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


/**
 * @ClassName: InvestServiceImpl
 * @Author: sky
 * @Date: 2022/2/24 21:26
 **/
@Service
public class InvestServiceImpl implements InvestService {
    @Autowired
    UserDao userDao;
    @Autowired
    InvestedThingsDao investedThingsDao;
    @Autowired
    InvestedThingsRecordsDao investedThingsRecordsDao;
    @Autowired
    FamilyTreeDao familyTreeDao;
    @Autowired
    RiskActivityDao riskActivityDao;
    @Autowired
    SellingRecordDao sellingRecordDao;

    @Override
    public void buyFund(InvestedThings invest) {
        investedThingsDao.insert(invest.getIdentity(),invest.getFundName(),invest.getCategory(),invest.getValue(),invest.getTimeStamp(),invest.getProportion());
        investedThingsRecordsDao.insertInvestedThingsRecord(invest.getIdentity(),invest.getFundName(),invest.getCategory(),invest.getValue(),invest.getTimeStamp(),false, 0);
    }

    @Override
    public void addAmount(String identity, Double amount,String timestamp) {
        if(amount<=0) return;
        List<InvestedThings> investedThingsList = investedThingsDao.selectAllValidByIdentity(identity);
        for(InvestedThings investedThings:investedThingsList){
            double add_amount = investedThings.getProportion()*amount;
            investedThingsDao.updateValueByIdentityAndFundName(identity,investedThings.getFundName(),investedThings.getValue()+add_amount);
            investedThingsRecordsDao.insertInvestedThingsRecord(identity,investedThings.getFundName(),investedThings.getCategory(),add_amount,timestamp,false,0);
        }
    }

    @Override
    public void changePortfolio(List<InvestedThings> investedThingsList) {
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0,10);
        User user = userDao.selectByIdentity(investedThingsList.get(0).getIdentity());
        double values = 0.00;
        if(investedThingsDao.amountSum(user.getIdentity())!=null) values = investedThingsDao.amountSum(user.getIdentity());
        sellAll(user.getIdentity(),values,timestamp,true);
        for(InvestedThings investedThings:investedThingsList){
            investedThings.setTimeStamp(timestamp);
            buyFund(investedThings);
        }
    }

    @Override
    public String sellFund(String identity, Double amount,boolean confirmed) {
        User user = userDao.selectByIdentity(identity);
        if(user==null) return null;
        String timeStamp = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0,10);
        int frequency = sellingRecordDao.frequencyIn30Days(identity,timeStamp);
        double amountSum = amount;
        if(sellingRecordDao.amountIn30Days(identity,timeStamp)!=null) amountSum += sellingRecordDao.amountIn30Days(identity,timeStamp);
        //判断红色风险
        if(user.getRiskLevel()==2) return "Red Risk";
        if(amountSum >= user.getLimit()){
            userDao.updateRiskLevelByIdentity(2,user.getIdentity());
            riskActivityDao.insertRiskActivity(user.getIdentity(),user.getFamilyID(),timeStamp,amount,"To be input by user");
            familyTreeDao.updateRedLevelNumberByFamilyID(user.getFamilyID(),familyTreeDao.getRedLevelNumberByFamilyID(user.getFamilyID())+1);
            return "Red Risk";
        }


        //判断重复支付
        if(!confirmed){
            if(amount.equals(sellingRecordDao.lastAmount(identity))){
                return "确定再次支付吗";
            }
        }
//        //判断黄色风险
//        if(frequency >= user.getFrequency()){
//            userDao.updateRiskLevelByIdentity(1,user.getIdentity());
//            completeSell(identity,amount,timeStamp,false);
//            if(sellingRecordDao.isYellowIn30Days(identity,timeStamp)) return "系统检测到您的交易频率多次超出之前所设定限额，请问您是否需要修改限额";
//            else return "Yellow Risk";
//        }

        List<Double> amountIn90Days = sellingRecordDao.amountIn90Days(identity);
        double D=0.00;
        for(Double p:amountIn90Days){
            if(p==null) p=0.00;
            D += Math.abs(p-user.getLimit());
        }
        if(D/3>0.05){
            userDao.updateRiskLevelByIdentity(1,user.getIdentity());
            completeSell(identity,amount,timeStamp,false);
            return "系统检测到您连续3个30天内的交易金额都十分接近之前所设定限额，请问您是否需要修改限额";
        }


        completeSell(identity,amount,timeStamp,false);
        return "Success!";
    }

    @Override
    public String updateDescription(String identity, String description) {
        if(description==null) return "Description cannot be null!";
        riskActivityDao.updateDescription(identity, description);
        return "Success!";
    }


    public void completeSell(String identity, Double amount, String timestamp, boolean isExempted){
        double values = investedThingsDao.amountSum(identity);
        if(amount>=values) sellAll(identity,values,timestamp,isExempted);
        else {
            sellingRecordDao.insertSellActivity(identity,timestamp,amount,isExempted);
            List<InvestedThings> investedThingsList = investedThingsDao.selectAllValidByIdentity(identity);
            for(InvestedThings investedThings:investedThingsList){
                double sub_amount = investedThings.getProportion()*amount;
                investedThingsDao.updateValueByIdentityAndFundName(identity,investedThings.getFundName(),investedThings.getValue()-sub_amount);
                investedThingsRecordsDao.insertInvestedThingsRecord(identity,investedThings.getFundName(),investedThings.getCategory(),sub_amount,timestamp,isExempted,1);
            }
        }


    }

    public void sellAll(String identity, Double amount, String timestamp, boolean isExempted){
        sellingRecordDao.insertSellActivity(identity,timestamp,amount,isExempted);
        List<InvestedThings> investedThingsList = investedThingsDao.selectAllValidByIdentity(identity);
        investedThingsDao.deleteByIdentity(identity);
        for (InvestedThings investedThings:investedThingsList){
            double sub_amount = investedThings.getProportion()*amount;
            investedThingsRecordsDao.insertInvestedThingsRecord(identity,investedThings.getFundName(),investedThings.getCategory(),sub_amount,timestamp,isExempted,1);
        }

    }



}
