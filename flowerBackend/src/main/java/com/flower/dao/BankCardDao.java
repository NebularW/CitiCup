package com.flower.dao;


import com.flower.service.entity.BankCard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BankCardDao {
    void insertBankCard(String ownerIdentity, String cardID, String ownerName, String phoneNumber); //添加银行卡

    List<BankCard> findBankCardByOwnerIdentity(String ownerIdentity); //获得银行卡

    void deleteByOwnerIdentityAndCardID(String ownerIdentity, String cardID); //删除银行卡
}
