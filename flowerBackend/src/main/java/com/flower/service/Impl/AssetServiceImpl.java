package com.flower.service.Impl;

import com.flower.Utils;
import com.flower.Vo.FinancialAssetVo;
import com.flower.Vo.HistoryBenefitsVo;
import com.flower.Vo.HomePageVo;
import com.flower.dao.InvestedThingsDailyBenefitsDao;
import com.flower.dao.InvestedThingsDao;
import com.flower.dao.InvestedThingsRecordsDao;
import com.flower.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName: AssetServiceImpl
 * @Author sky
 * @Date 2022/3/7 21:12
 */
@Service
public class AssetServiceImpl implements AssetService {
    Utils utils = new Utils();
    @Autowired
    InvestedThingsDao investedThingsDao;
    @Autowired
    InvestedThingsDailyBenefitsDao investedThingsDailyBenefitsDao;
    @Autowired
    InvestedThingsRecordsDao investedThingsRecord;

    @Override
    public HomePageVo getHomePageInfo(String identity) {
        HomePageVo homePageInfo = new HomePageVo();
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10);
        if (investedThingsDao.amountSum(identity) != null)
            homePageInfo.setAssets(investedThingsDao.amountSum(identity));
        else homePageInfo.setAssets(0.00);
        if (investedThingsDailyBenefitsDao.findBenefitsYesterdaySumByOwnerIdentityAndTimeStamp(identity, timestamp) != null)
            homePageInfo.setBenefitsYesterday(investedThingsDailyBenefitsDao.findBenefitsYesterdaySumByOwnerIdentityAndTimeStamp(identity, timestamp));
        else homePageInfo.setBenefitsYesterday(0.00);
        if (investedThingsDao.selectAllValidByIdentity(identity) != null)
            homePageInfo.setInvestedThingsList(investedThingsDao.selectAllValidByIdentity(identity));
        if (investedThingsDailyBenefitsDao.findAllByIdentity(identity) != null)
            homePageInfo.setBenefitsCurve(investedThingsDailyBenefitsDao.findAllByIdentity(identity));
        return homePageInfo;
    }

    @Override
    public FinancialAssetVo getFinancialAsset(String identity) {
        FinancialAssetVo financialAssetVo = new FinancialAssetVo();
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10);


        financialAssetVo.setAssets(0.00);
        financialAssetVo.setBenefitsYesterday(0.00);
        financialAssetVo.setBenefitsSum(0.00);
        financialAssetVo.setInvestedThingsList(null);
        if (investedThingsDao.amountSum(identity) != null)
            financialAssetVo.setAssets(investedThingsDao.amountSum(identity));
        if (investedThingsDailyBenefitsDao.findBenefitsYesterdaySumByOwnerIdentityAndTimeStamp(identity, timestamp) != null)
            financialAssetVo.setBenefitsYesterday(investedThingsDailyBenefitsDao.findBenefitsYesterdaySumByOwnerIdentityAndTimeStamp(identity, timestamp));
        if (investedThingsDailyBenefitsDao.findBenefitsSumByOwnerIdentityAndTimeStamp(identity) != null)
            financialAssetVo.setBenefitsSum(investedThingsDailyBenefitsDao.findBenefitsSumByOwnerIdentityAndTimeStamp(identity));
        if (investedThingsDao.selectAllValidByIdentity(identity) != null)
            financialAssetVo.setInvestedThingsList(investedThingsDao.selectAllValidByIdentity(identity));

        return financialAssetVo;
    }

    @Override
    public List<HistoryBenefitsVo> getHistoryBenefits(String identity, String timestamp) {
        if (identity == null) return null;
        if (timestamp == null) timestamp = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10);
        String timestampForward;
        List<HistoryBenefitsVo> historyBenefitsVoList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            HistoryBenefitsVo historyBenefitsVo = new HistoryBenefitsVo();
            timestampForward = utils.getForwardTimeStamp(timestamp, i);
            if (investedThingsDailyBenefitsDao.findByIdentityAndTimestamp(identity, timestamp) != null)
                historyBenefitsVo.setInvestedThingsDailyFundBenefits(investedThingsDailyBenefitsDao.findByIdentityAndTimestamp(identity, timestampForward));
            if (investedThingsDailyBenefitsDao.findBenefitsDaySumByOwnerIdentityAndTimeStamp(identity, timestampForward) != null)
                historyBenefitsVo.setBenefitsDaySum(investedThingsDailyBenefitsDao.findBenefitsDaySumByOwnerIdentityAndTimeStamp(identity, timestampForward));
            if (investedThingsRecord.findByTimestampAndIdentity(timestampForward, identity) != null)
                historyBenefitsVo.setInvestedThingsRecordList(investedThingsRecord.findByTimestampAndIdentity(timestampForward, identity));
            historyBenefitsVoList.add(historyBenefitsVo);
        }
        return historyBenefitsVoList;
    }
}
