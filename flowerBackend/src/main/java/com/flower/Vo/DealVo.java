package com.flower.Vo;

import lombok.Data;

/**
 * @ClassName: DealVo
 * @Description: 封装卖出基金对象
 * @Author: sky
 * @Date: 2022/3/5 15:45
 **/
@Data
public class DealVo {
    private String identity; //openid

    private Double amount;   //追加投入/卖出的金额

    private boolean confirmed = false;  //用于两次相同金额的卖出交易后，确认是否继续
}
