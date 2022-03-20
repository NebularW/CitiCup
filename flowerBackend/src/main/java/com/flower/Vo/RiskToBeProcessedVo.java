package com.flower.Vo;

import com.flower.service.entity.RiskActivity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: RiskToBeProcessedVo
 * @Description: 用户登录界面时，传送到前端，该用户是否被冻结以及是否需要对其被冻结账户的家人的风险行为做出操作
 * @Author: sky
 * @Date: 2022/2/26 20:54
 **/
@Data
public class RiskToBeProcessedVo {
    private Integer redNum;  //家庭中红色风险人数

    private boolean isFrozen;  //自己账户是否被冻结

    private List<RiskActVo> riskActVoList;  //需要自己做出回应的家人风险行为

}
