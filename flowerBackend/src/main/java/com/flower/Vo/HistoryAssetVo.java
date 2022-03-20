package com.flower.Vo;

import lombok.Data;

/**
 * @ClassName: HistoryAssetVo
 * @Description: 封装前端发来的请求查看历史记录的数据
 * @Author: sky
 * @Date: 2022/3/13 14:18
 **/
@Data
public class HistoryAssetVo {
    String identity;    //用户openid
    String timestamp;   //时间戳，格式为2022-03-03
}
