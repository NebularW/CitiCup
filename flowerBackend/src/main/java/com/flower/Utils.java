package com.flower;

import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: Utils
* @Author sky
* @Date 2022/3/7 22:05
*/
public class Utils {
    public static Map<String, Double> fundYieldRates = new HashMap<>();

    static{
        fundYieldRates.put("工银瑞信", 0.4276947550241623);
        fundYieldRates.put("富国中证煤炭A", 0.1835196861713485);
        fundYieldRates.put("华安创业板", 0.3143013575425357);
        fundYieldRates.put("嘉实原油", 0.04565095934666025);
        fundYieldRates.put("万家上证", 0.07937749427793499);
        fundYieldRates.put("国泰上证", 0.025012218698611217);
        fundYieldRates.put("海富通上证", 0.00525051512088535);
        fundYieldRates.put("南方金利A", -0.005004210882228878);
        fundYieldRates.put("泰信增强收益A", 0.030960887033505517);
        fundYieldRates.put("货币ETF", 1.0958192759400383e-07);
    }

    public String getForwardTimeStamp(String timestamp, int forward){
        //2022-03-12
        //0123456789
        StringBuilder res = new StringBuilder();
        int month = Integer.parseInt(timestamp.substring(5,7));
        int day = Integer.parseInt(timestamp.substring(8,10));
        if(day-forward>=1){
            if(day-forward<=9) res.append(timestamp, 0, 8).append("0").append(day-forward);
            else res.append(timestamp, 0, 8).append(day-forward);
        }
        else {
            switch (month-1){
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                    day += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    day += 30;
                    break;
            }
            if(month-1<=9) res.append(timestamp,0,5).append("0").append(month-1);
            else res.append(timestamp,0,5).append(month-1);
            if(day-forward<=9) res.append("-0").append(day-forward);
            else res.append("-").append(day-forward);

        }
        return res.toString();
    }

    public boolean isIn3Days(String timestamp_act,String timestamp_today){
        boolean res = false;
        int month1 = Integer.parseInt(timestamp_act.substring(5,7));
        int day1 = Integer.parseInt(timestamp_act.substring(8,10));
        int month2 = Integer.parseInt(timestamp_today.substring(5,7));
        int day2 = Integer.parseInt(timestamp_today.substring(8,10));
        if(month1==month2&&day2-day1>=3) res = true;
        return res;
    }
}
