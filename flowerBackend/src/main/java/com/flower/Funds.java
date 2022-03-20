package com.flower;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Funds
 * @Description: 存放各支基金的详细信息
 * @Author: sky
 * @Date: 2022/3/6 14:57
 **/
public class Funds {
    private final String[] fundA1 =  {"21铁投01 6.66%", "20际华01 6.65%", "19长城 6.59%", "19广发 6.59%", "21国君 6.59%"};
    private final String[] fundA2 =  {"21附息 52.55%", "21附息 26.45%", "21进出 7.84%", "20附息 5.24%", "19国开 5.23%"};
    private final String[] fundA3 =  {"21南京银行CD109 3.09%", "21宁波银行CD358 1.84% ", "21民生银行CD370 1.22%", "21渤海银行CD491 1.22%", "21徽商银行CD159 1.10%"};
    private final String[] fundA4 =  {"19南建02 1.81%", "19武管廊 1.63%", "17苏新02 1.51%", "20明城02 1.51%", "19椒江01 1.37%"};
    private final String[] fundA5 =  {"21附息国债11 26.85%", "21国债11 24.02%", "19附息国债16 21.89%", "19国债16 13.07%", "21国开03 5.14%"};
    private final String[] fundB1 =  {"美锦能源 9.72%", "中国神华 9.60%", "永泰能源 9.31%", "陕西煤业 8.76%", "兖矿能源 5.57%", "山西焦煤 4.49%", "华阳股份 3.78%", "潞安环能 3.59%", "中煤能源 3.05%", "电投能源 3.03%"};
    private final String[] fundB2 =  {"货币基金 100.00%"};
    private final String[] fundB3 =  {"杉杉股份 4.68%", "天合光能 4.56%", "天齐锂业 4.51%", "诺德股份 4.03%", "鹏辉能源 3.65%", "宁德时代 3.35%", "捷佳伟创 3.18%", "德业股份 3.00%", "晶澳科技 2.93%", "阳光电源 2.60%"};
    private final String[] fundB4 =  {"宁德时代 22.20%", "东方财富 9.62%", "迈瑞医疗 5.26%", "亿纬锂能 4.81%", "阳光电源 4.61%", "汇川技术 3.86%", "爱尔眼科 3.17%", "智飞生物 2.96%", "沃森生物 2.93%", "先导智能 2.42%"};
    private final String[] fundB5 =  {"贵州茅台 15.33%", "招商银行 7.40%", "中国平安 6.69%", "隆基股份 4.59%", "兴业银行 3.39%", "长江电力 3.16%", "伊利股份 3.11%", "药明康德 3.00%", "药明康德 2.77%", "中信证券 2.75%"};





    private final Map<String,String[]> fundInfo = new HashMap<String,String[]>(){{
        put("南方金利A",fundA1);
        put("泰信增强收益A",fundA2);
        put("华安日日鑫货币H",fundA3);
        put("海富通上证城投债ETF",fundA4);
        put("国泰上证5年期国债ETF",fundA5);
        put("富国中证煤炭A",fundB1);
        put("嘉实原油",fundB2);
        put("工银瑞信生态环境",fundB3);
        put("华安创业板50ETF",fundB4);
        put("万家上证50ETF",fundB5);
    }};



    public String[] getFundInfo(String fundName){
        return fundInfo.get(fundName);
    }
}
