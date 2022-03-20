package com.flower.service.entity;

import lombok.Data;

@Data
public class User {

    private String identity; //openid

    private String userName; //用户名

    private Integer sex; //value = "性别"

    private String address; //"微信获取用户地址"

    private String phoneNumber; //电话号码 TODO 霁昀 这个应该在loginVo里面吧？

    private String avatarUrl; //头像地址

    private Integer familyID; //所属家庭的ID,初始为null, 表示没有加入任何家庭

    private String relationToFamilyHolder; //相对于户主的关系

    private Integer fontSize; //字体 TODO 大家 需要一个默认值

    private String appString; //为之后的长辈协助修改应用数量 TODO 大家 需要一个默认值

    private Integer riskLevel; //用户的风险等级, 0表示green, 1表示yellow, 2表示red

    private Integer frequency = 10;  //频率上限(以投资组合为单位)

    private Double limit = 100000.00; //额度上限(精确到2位小数)

    private Integer mode; //0表示青年模式, 1表示老年模式

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }
}
