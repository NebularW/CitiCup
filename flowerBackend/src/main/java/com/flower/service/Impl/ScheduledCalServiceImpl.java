package com.flower.service.Impl;

import com.flower.Utils;
import com.flower.dao.InvestedThingsDailyBenefitsDao;
import com.flower.dao.InvestedThingsDao;
import com.flower.dao.UserDao;
import com.flower.service.ScheduledCalService;
import com.flower.service.entity.InvestedThings;
import com.flower.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledCalServiceImpl implements ScheduledCalService {

    @Autowired
    UserDao userDao;

    @Autowired
    InvestedThingsDao investedThingsDao;

    @Autowired
    InvestedThingsDailyBenefitsDao investedThingsDailyBenefitsDao;

    @Override
    @Scheduled(cron = "0 0 0 * * ?") //每天0:00执行的条件
    //@Scheduled(cron = "0 0/1 * * * ?") //每2秒钟执行的语句
    public void calculate() {
        List<User> users = userDao.selectAllUser();
        for (User user : users) {
            String identity = user.getIdentity();

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String timeStamp = formatter.format(date);

            List<InvestedThings> investedThings = investedThingsDao.selectAllValidByIdentity(user.getIdentity());
            for (InvestedThings investedThing : investedThings) {
                String fundName = investedThing.getFundName();
                String category = investedThing.getCategory();
                Double oldValue = investedThing.getValue();
                Double yieldRate = Utils.fundYieldRates.get(fundName);
                Double benefits = oldValue * yieldRate;
                Double newValue = benefits + oldValue;

                investedThingsDao.updateValueByIdentityAndFundName(identity, fundName, newValue);
                investedThingsDailyBenefitsDao.insert(identity, fundName, category, timeStamp, benefits);
            }
        }
    }
}
