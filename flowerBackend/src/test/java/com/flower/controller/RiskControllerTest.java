package com.flower.controller;


import com.flower.Vo.FrequencyAndLimitVo;
import com.flower.service.entity.ResponseAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @title: RiskControllerTest
 * @Author: Stanton JY & Sky
 * @Date: 2022/3/5 15:33
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class RiskControllerTest {
    @Autowired
    RiskController controller;
    String identity = "wx2";

    //正常情况
    @Test
    public void updateFL1() {
        Integer frequency = 10;
        Double limit = 100000.00;
        String res = controller.updateFrequencyAndLimit(new FrequencyAndLimitVo(identity, frequency, limit));
        Assertions.assertEquals("Success!", res);
    }

    //值为负数
    @Test
    public void updateFL2() {
        Integer frequency = -10;
        Double limit = -100000.00;
        String res = controller.updateFrequencyAndLimit(new FrequencyAndLimitVo(identity, frequency, limit));
        Assertions.assertEquals("frequency and limit should be positive!", res);
    }

    //值为null
    @Test
    public void updateFL3() {
        Integer frequency = null;
        Double limit = null;
        String res = controller.updateFrequencyAndLimit(new FrequencyAndLimitVo(identity, frequency, limit));
        Assertions.assertEquals("frequency and limit cannot be null!", res);
    }

    //identity为null
    @Test
    public void updateFL4() {
        Integer frequency = 10;
        Double limit = 100000.00;
        String res = controller.updateFrequencyAndLimit(new FrequencyAndLimitVo(null, frequency, limit));
        Assertions.assertEquals("Success!", res);
    }

    //identity不存在
    @Test
    public void updateFL5() {
        Integer frequency = 10;
        Double limit = 100000.00;
        String res = controller.updateFrequencyAndLimit(new FrequencyAndLimitVo("wfs", frequency, limit));
        Assertions.assertEquals("Success!", res);
    }

    //无家庭
    @Test
    public void updateFL6() {
        Integer frequency = 10;
        Double limit = 100000.00;
        String res = controller.updateFrequencyAndLimit(new FrequencyAndLimitVo("wx_aklsgdjhlkajgaafds", frequency, limit));
        Assertions.assertEquals("Success!", res);
    }


    @Test
    public void familyRiskTest1(){
        System.out.println(controller.familyRisk(identity));
    }

    //identity为null
    @Test
    public void familyRiskTest2(){
        Assertions.assertNull(controller.familyRisk(null));
    }

    //identity不存在
    @Test
    public void familyRiskTest3(){
        Assertions.assertNull(controller.familyRisk("null"));
    }

    //家庭不存在
    @Test
    public void familyRiskTest4(){
        Assertions.assertNull(controller.familyRisk("wx_aklsgdjhlkajgaafds"));
    }



    @Test
    public void riskToBeProcessedTest(){
        System.out.println(controller.riskToBeProcessed(identity));
    }

    //家庭不存在
    @Test
    public void responseToRiskTest1(){
        ResponseAction responseAction = new ResponseAction();
        responseAction.setFromIdentity("wx3");
        responseAction.setToIdentity("wx4");
        responseAction.setActionType(0);
        String res = controller.responseToRisk(responseAction);
        Assertions.assertEquals("No family!", res);
    }

    //identity为null
    @Test
    public void responseToRiskTest2(){
        ResponseAction responseAction = new ResponseAction();
        responseAction.setFromIdentity(null);
        responseAction.setToIdentity(null);
        responseAction.setActionType(0);
        String res = controller.responseToRisk(responseAction);
        Assertions.assertEquals("Identity cannot be null!", res);
    }



    //ResponseAction为null
    @Test
    public void responseToRiskTest3(){
        String res = controller.responseToRisk(null);
        Assertions.assertEquals("ResponseAction cannot be null!", res);
    }


}
