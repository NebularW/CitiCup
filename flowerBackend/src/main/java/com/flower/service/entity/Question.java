package com.flower.service.entity;

import lombok.Data;

/**
 * 该类对应智能问答的热点关注，通过查看searchTimes来排序
 */
@Data
public class Question {
    private Integer id;

    private String description; //问答内容

    private Integer searchTimes; //问题被用户搜索的总共次数

    private String type; //对应问答的领域:"长辈协助";"风险管控";"收益记录";"其他功能"
}
