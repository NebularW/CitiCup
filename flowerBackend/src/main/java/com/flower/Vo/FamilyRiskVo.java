package com.flower.Vo;

import com.flower.service.entity.RiskActivity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: FamilyRiskVo
 * @Description: 该类用于封装家庭关联账户需要用到的家人风险状况及历史风险交易记录等属于
 * @Author sky
 * @Date 2022/2/25 21:04
 */
@Data
public class FamilyRiskVo {
    private String identity; //openid

    private String userName; //用户名

    private String avatarUrl; //头像地址

    private Integer familyID; //所属家庭的ID,初始为null, 表示没有加入任何家庭

    private Integer riskLevel; //用户的风险等级, 0表示green, 1表示yellow, 2表示red

    private List<RiskActivity> riskActivities;

}
