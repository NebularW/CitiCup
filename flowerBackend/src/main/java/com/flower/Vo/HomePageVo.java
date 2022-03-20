package com.flower.Vo;

import com.flower.service.entity.InvestedThings;
import com.flower.service.entity.InvestedThingsDailyBenefits;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: HomePageVo
 * @Description: 用于封装主页信息
 * @Author: sky
 * @Date: 2022/3/8 22:16
 **/
@Data
public class HomePageVo {
    private double assets;          //用户总资产

    private double benefitsYesterday;   //昨日收益

    private List<InvestedThings> investedThingsList;    //用户当前投资组合

    private List<InvestedThingsDailyBenefits> benefitsCurve; //收益情况曲线
}
