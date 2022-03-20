package com.flower.Vo;

import com.flower.service.entity.InvestedThings;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FinancialAssetVo
 * @Description: 封装理财页面的资产信息
 * @Author sky
 * @Date 2022/3/7 20:58
 */
@Data
public class FinancialAssetVo {
    private double assets;          //用户总资产

    private double benefitsYesterday;   //昨日收益

    private double benefitsSum;     //累计收益

    private List<InvestedThings> investedThingsList;    //用户当前投资组合

}
