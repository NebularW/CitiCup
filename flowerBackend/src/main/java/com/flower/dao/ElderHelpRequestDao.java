package com.flower.dao;

import com.flower.service.entity.ElderHelpRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ElderHelpRequestDao {
    boolean findAllByFromAndTo(String fromIdentity, String toIdentity);  //得知授权情况

    void addAuthorizationRequest(String fromIdentity, String toIdentity); //一开始没有授权记录就新增记录,IsAuthorized=False

    List<ElderHelpRequest> findRequestsByToIdentity(String toIdentity); //每次成员进入的时候查看是否有人向他发出请求

    void updateIsAuthorizedByFromAndTo(String fromIdentity, String toIdentity); //确认授权
}
