package com.flower.service;

public interface ScheduledCalService {
    /**
     * 每天0:00根据模型给出的收益率，计算所有用户当前持有基金的收益，并且同步到investedThings和investedThingsDailyBenefits
     */
    void calculate();
}
