package com.flower.dao;

import com.flower.service.entity.AuthorizationForMemberCard;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthorizationForMemberCardDao {
    AuthorizationForMemberCard findAllByFromAndTo(String fromIdentity, String toIdentity); //得知授权情况

    void addAuthorizationRequest(String fromIdentity, String toIdentity); //一开始没有授权记录就新增记录,IsAuthorized=False

    List<AuthorizationForMemberCard> findRequestsByToIdentity(String toIdentity);//每次成员进入的时候查看是否有人向他发出请求

    void updateIsAuthorizedByFromAndTo(String fromIdentity, String toIdentity, boolean isAuthorized); //确认授权

    void deleteAuthorizationRequest(String fromIdentity, String toIdentity); //被授权人拒绝授权时调用这个删除接口

}
