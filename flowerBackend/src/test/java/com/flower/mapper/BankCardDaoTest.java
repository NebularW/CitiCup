package com.flower.mapper;

import com.flower.FlowerBackendApplication;
import com.flower.dao.BankCardDao;
import com.flower.service.entity.BankCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class BankCardDaoTest {

    @Autowired
    BankCardDao bankCardDao;

    @Test
    public void bankCardDaoTest(){
        String lineSeparator = System.lineSeparator();
        BankCard bankCard = new BankCard();
        bankCard.setOwnerIdentity("wx_aklsgdjhlkajgaafds");
        bankCard.setCardID("1234567898765432");
        bankCard.setOwnerName("wfs");
        bankCard.setPhoneNumber("18800000000");

        System.out.println("Input:" + lineSeparator +
                "  ownerIdentity: wx_aklsgdjhlkajgaafds" + lineSeparator +
                "  cardID: 1234567898765432" + lineSeparator +
                "  ownerName: wfs" + lineSeparator +
                "  phoneNumber: 18800000000"+ lineSeparator);

        bankCardDao.insertBankCard(bankCard.getOwnerIdentity(), bankCard.getCardID(), bankCard.getOwnerName(), bankCard.getPhoneNumber());

        Assertions.assertEquals(bankCard, bankCardDao.findBankCardByOwnerIdentity(bankCard.getOwnerIdentity()).get(0));

        bankCardDao.deleteByOwnerIdentityAndCardID("wx_aklsgdjhlkajgaafds", "1234567898765432");

        Assertions.assertTrue(bankCardDao.findBankCardByOwnerIdentity(bankCard.getOwnerIdentity()).isEmpty());
    }


}
