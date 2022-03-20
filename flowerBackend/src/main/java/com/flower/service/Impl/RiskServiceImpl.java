package com.flower.service.Impl;

import com.flower.Utils;
import com.flower.Vo.FamilyRiskVo;
import com.flower.Vo.RiskActVo;
import com.flower.Vo.RiskToBeProcessedVo;
import com.flower.dao.*;
import com.flower.service.entity.FamilyTree;
import com.flower.service.entity.ResponseAction;
import com.flower.service.entity.RiskActivity;
import com.flower.service.entity.User;
import com.flower.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 * @ClassName: RiskServiceImpl
 * @Author: sky
 * @Date: 2022/2/25 1:00
 **/
@Service
public class RiskServiceImpl implements RiskService {
    @Autowired
    UserDao userDao;
    @Autowired
    FamilyTreeDao familyTreeDao;
    @Autowired
    RiskActivityDao riskActivityDao;
    @Autowired
    ResponseActionDao responseActionDao;
    @Autowired
    InvestServiceImpl investService;

    Utils utils = new Utils();

    private Integer redLevelNum(String identity) {
        User user = userDao.selectByIdentity(identity);
        Integer familyID = user.getFamilyID();
        return familyTreeDao.getRedLevelNumberByFamilyID(familyID);
    }

    @Override
    public void thaw(String identity) {
        User user = userDao.selectByIdentity(identity);
        FamilyTree familyTree = familyTreeDao.selectByFamilyID(user.getFamilyID());
        userDao.updateRiskLevelByIdentity(0,identity);
        familyTreeDao.updateRedLevelNumberByFamilyID(user.getFamilyID(),familyTree.getRedLevelNumber()-1);
        RiskActivity riskActivity = riskActivityDao.selectUncompletedByIdentity(user.getIdentity());
        if(!responseActionDao.isRefused(identity)) investService.completeSell(identity, riskActivity.getAmount(), riskActivity.getTimeStamp(), true);
        riskActivityDao.updateCompletedByIdentity(true, user.getIdentity());
        responseActionDao.deleteByToIdentity(identity);
    }

    @Override
    public List<FamilyRiskVo> familyRisk(String identity) {
        User user_ = userDao.selectByIdentity(identity);
        if(user_==null) return null;
        if(user_.getFamilyID()==null) return null;
        FamilyTree familyTree = familyTreeDao.selectByFamilyID(user_.getFamilyID());
        List<FamilyRiskVo> familyRiskVos = new ArrayList<>();
        List<User> users = new ArrayList<>();
        for (String id : userDao.selectByFamilyId(familyTree.getId())) {
            users.add(userDao.selectByIdentity(id));
        }
        for (User user : users) {
            FamilyRiskVo familyRiskVo = new FamilyRiskVo();
            familyRiskVo.setIdentity(user.getIdentity());
            familyRiskVo.setUserName(user.getUserName());
            familyRiskVo.setFamilyID(user.getFamilyID());
            familyRiskVo.setAvatarUrl(user.getAvatarUrl());
            familyRiskVo.setRiskLevel(user.getRiskLevel());
            familyRiskVo.setRiskActivities(riskActivityDao.selectByIdentity(user.getIdentity()));
            familyRiskVos.add(familyRiskVo);
        }
        return familyRiskVos;
    }

    @Override
    public RiskToBeProcessedVo riskToPro(String identity) {
        RiskToBeProcessedVo riskToBeProcessedVo = new RiskToBeProcessedVo();
        User user = userDao.selectByIdentity(identity);
        if(user==null) return null;
        if(user.getFamilyID()!=null) riskToBeProcessedVo.setRedNum(redLevelNum(identity));
        riskToBeProcessedVo.setFrozen(user.getRiskLevel() == 2);

        //若账户未被冻结，则需要帮助家人解冻
        if (!riskToBeProcessedVo.isFrozen()) {
            List<RiskActivity> riskActivities = riskActivityDao.selectByFamilyID(user.getFamilyID());
            List<RiskActVo> riskActVoList = new ArrayList<>();
            for (RiskActivity riskActivity : riskActivities) {
                ResponseAction responseAction = responseActionDao.selectByToIdentityAndFromIdentity(riskActivity.getIdentity(), identity);
                if (responseAction == null) {
                    User user_red = userDao.selectByIdentity(riskActivity.getIdentity());
                    RiskActVo riskActVo = new RiskActVo(user_red.getIdentity(),user_red.getUserName(),user_red.getAvatarUrl(),riskActivity.getTimeStamp(),riskActivity.getAmount(),riskActivity.getDescription());
                    riskActVoList.add(riskActVo);
                }
            }
            riskToBeProcessedVo.setRiskActVoList(riskActVoList);

        }else {
            RiskActivity riskActivity_this = riskActivityDao.selectUncompletedByIdentity(identity);
            String timeStamp = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0,10);
            if(utils.isIn3Days(riskActivity_this.getTimeStamp(),timeStamp)) thaw(identity);
        }

        return riskToBeProcessedVo;
    }

    @Override
    public String responseToRisk(List<ResponseAction> responseActions) {
        ResponseAction responseAction = responseActions.get(0);
        if (responseAction == null) return "ResponseAction cannot be null!";
        if(responseAction.getToIdentity()==null||responseAction.getFromIdentity()==null) return "Identity cannot be null!";
        User user = userDao.selectByIdentity(responseActions.get(0).getFromIdentity());
        FamilyTree familyTree = familyTreeDao.selectByFamilyID(user.getFamilyID());
        if(familyTree==null) return "No family!";
        Integer responseNum = familyTree.getMemberNumber() - familyTree.getRedLevelNumber();
        User userR = userDao.selectByIdentity(responseAction.getToIdentity());
        if(userR.getRiskLevel()==2) responseActionDao.insertResponseAction(responseAction.getFromIdentity(), responseAction.getToIdentity(), responseAction.getActionType());
        if (responseActionDao.toIdentityNum(responseAction.getToIdentity())>=responseNum) thaw(responseAction.getToIdentity());
        return "Success!";
    }

    @Override
    public String updateFrequencyAndLimit(String identity, Integer frequency, Double limit) {
        if (frequency == null || limit == null) return "frequency and limit cannot be null!";
        if(frequency<=0||limit<=0) return "frequency and limit should be positive!";
        userDao.updateFrequencyByIdentity(identity, frequency);
        userDao.updateLimitByIdentity(identity, limit);
        return "Success!";
    }

}
