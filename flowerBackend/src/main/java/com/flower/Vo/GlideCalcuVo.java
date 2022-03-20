package com.flower.Vo;

import lombok.Data;

/**
 * @title: GlideCalcuVo
 * @Author: Stanton JY
 * @Date: 2022/3/15 11:43
 */
@Data
public class GlideCalcuVo {
    double alpha;
    double beta;
    int retire_year;
    double c;
    String gender;
    double M;
    double s;
//		:param alpha: 惩罚参数，问卷得到
//		:param beta: 主观跨期折现因子，问卷得到
//		:param retire_year: 退休年份，用户自定义
//		:param c: 缴费率，用户自定义
//		:param gender: 用户性别（与计算寿命有关）,"Male" 或 "Female"
//		:param M: 退休年龄
//		:param s: 养老金替代率，用户自定义
}

