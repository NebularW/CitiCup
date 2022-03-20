package com.flower.mapper;


import com.flower.FlowerBackendApplication;
import com.flower.dao.SellingRecordDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = FlowerBackendApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class SellingRecordDaoTest {

    @Autowired
    private SellingRecordDao sellingRecordDao;

    private String identity = "wx_aklsgdjhlkajgaafds";

    @Test
    public void insertSellActivityTest(){
        for (int i = 90; i < 150; i++)
        {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String timestamp = formatter.format(new Date(date.getTime() - (long) i * 24 * 60 * 60 * 1000));
            Double amount = (double)i;
            sellingRecordDao.insertSellActivity(identity, timestamp, amount, false);
        }
    }

    @Test
    public void amountIn90DaysTest(){
        List<Double> res = sellingRecordDao.amountIn90Days(identity);
        System.out.println(res);
    }
}
