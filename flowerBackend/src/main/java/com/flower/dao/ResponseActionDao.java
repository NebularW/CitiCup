package com.flower.dao;

import com.flower.service.entity.ResponseAction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ResponseActionDao {

    void insertResponseAction(String fromIdentity, String toIdentity, Integer actionType); //成员爆红后就加入处理

    //Completed 通过ToIdentity和fromIdentity，获取ResponseAction
    ResponseAction selectByToIdentityAndFromIdentity(String toIdentity, String fromIdentity);

    //Completed 获取该ToIdentity的所有responseAction数量
    Integer toIdentityNum(String toIdentity);

    //Completed 删除所有该toIdentity的responseAction
    void deleteByToIdentity(String toIdentity); //爆红解决后删除掉成员对于他的回应

    //Completed 是否有人拒绝toIdentity的风险操作
    boolean isRefused(String toIdentity);

}
