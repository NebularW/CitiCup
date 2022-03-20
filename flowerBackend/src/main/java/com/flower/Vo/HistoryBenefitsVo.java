package com.flower.Vo;

import com.flower.service.entity.InvestedThingsDailyBenefits;
import com.flower.service.entity.InvestedThingsRecord;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: HistoryBenefitsVo
 * @Description: 封装主页收益历史记录
 * @Author sky
 * @Date 2022/3/7 21:39
 */
@Data
public class HistoryBenefitsVo {
    private double benefitsDaySum;  //一天的总收益

    private List<InvestedThingsDailyBenefits> investedThingsDailyFundBenefits;   //一天各基金的收益

    private List<InvestedThingsRecord> investedThingsRecordList;    //一天的交易记录
}
