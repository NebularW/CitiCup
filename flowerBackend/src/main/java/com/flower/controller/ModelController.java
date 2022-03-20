package com.flower.controller;

import com.flower.Vo.GlideCalcuVo;
import com.flower.Vo.Suggestion;
import com.flower.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @title: ModelController
 * @Author: Stanton JY
 * @Date: 2022/3/15 0:36
 */
@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    ModelService modelService;

    @RequestMapping("/suggest")
    public ArrayList<Suggestion> suggestion(@RequestBody GlideCalcuVo glideCalcuVo) {
        ArrayList<Suggestion> ret = new ArrayList<Suggestion>();
        int lambda = 2;
        Double proportion = modelService.cal_glidepath(1 + glideCalcuVo.getAlpha()* 0.02, glideCalcuVo.getBeta(), glideCalcuVo.getRetire_year(), glideCalcuVo.getC(), glideCalcuVo.getGender(), glideCalcuVo.getM(), glideCalcuVo.getS());
        ret = compose(modelService.BL_calcu_w(lambda, "/root/Models/Data/Price_Data_High.xlsx"), proportion);
        ret.addAll(compose(modelService.BL_calcu_w(lambda, "/root/Models/Data/Price_Data_Low.xlsx"), 1 - proportion));
        return ret;
    }

    @RequestMapping("/glide")
    public ArrayList<Double> glide(@RequestBody GlideCalcuVo glideCalcuVo) {
        ArrayList<Double> res = modelService.getGlidePath(1 + glideCalcuVo.getAlpha() * 0.02, glideCalcuVo.getBeta(), glideCalcuVo.getRetire_year(), glideCalcuVo.getC(), glideCalcuVo.getGender(), glideCalcuVo.getM(), glideCalcuVo.getS());
        return res;
    }

    private ArrayList<Suggestion> compose(ArrayList<Suggestion> suggestions, Double proportion) {
        ArrayList<Suggestion> ret = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            ret.add(new Suggestion(suggestion.getFundName(), suggestion.getWeight() * proportion));
        }
        return ret;
    }
}
