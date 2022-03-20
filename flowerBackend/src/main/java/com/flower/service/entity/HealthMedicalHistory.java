package com.flower.service.entity;

import lombok.Data;

@Data
public class HealthMedicalHistory {
    private Integer id;

    private String identity; //openID

    private String diseaseName; //疾病名称

    private Boolean hasPerformedOperation; //是否动过手术

    private String announcement; //注意事项

    private String confirmedTime; //确诊时间

    public HealthMedicalHistory(String identity, String diseaseName, Boolean hasPerformedOperation, String announcement, String confirmedTime) {
        this.identity = identity;
        this.diseaseName = diseaseName;
        this.hasPerformedOperation = hasPerformedOperation;
        this.announcement = announcement;
        this.confirmedTime = confirmedTime;
    }

    public HealthMedicalHistory() {
    }
}
