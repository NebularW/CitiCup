package com.flower.Vo;

import lombok.Data;

/**
 * @ClassName: DescriptionVo
 * @Description: 封装用户描述风险对象
 * @Author: sky
 * @Date: 2022/3/6 17:38
 **/
@Data
public class DescriptionVo {
    private String identity;    //用户openid

    private String description; //用户对此次红色风险交易的描述
}
