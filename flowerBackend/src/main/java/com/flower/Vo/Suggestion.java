package com.flower.Vo;

import lombok.Data;

/**
 * @title: Suggestion
 * @Author: Stanton JY
 * @Date: 2022/3/13 14:58
 */
@Data
public class Suggestion {
    String fundName;
    Double weight;//配比

    public Suggestion(String fundName, Double weight) {
        this.fundName = fundName;
        this.weight = weight;
    }
}
