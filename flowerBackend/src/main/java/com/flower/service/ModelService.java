package com.flower.service;

import com.flower.Vo.Suggestion;

import java.util.ArrayList;

public interface ModelService {
    public ArrayList<Suggestion> BL_calcu_w(int lambda, String file);

    public Double cal_lambda();

    public Double cal_glidepath(double alpha, double beta, int retire_year, double c, String gender, double M, double s, double miu, double lmd, double VL1, double VL2, double u1, double u2, double sigma1, double sigma2);

    public Double cal_glidepath(double alpha, double beta, int retire_year, double c, String gender, double M, double s);

    public  ArrayList<Double> getGlidePath(double alpha, double beta, int retire_year, double c, String gender, double M, double s);
}
