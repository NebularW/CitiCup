package com.flower.service.Impl;

import com.flower.Vo.Suggestion;
import com.flower.service.ModelService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @title: ModelService
 * @Author: Stanton JY
 * @Date: 2022/3/13 15:02
 */
@Service
public class ModelServiceImpl implements ModelService {
    public ArrayList<Suggestion> BL_calcu_w(int lambda, String file) {
        //修改参数
        FileWriter args = null;
        try {
            args = new FileWriter("/root/Models/BL/args.py");
            args.write("LAMBDA = " + lambda + '\n');
            args.write("PRICE_FILENAME = " + '\"' + file + '\"' + '\n');
            args.flush();
            args.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python /root/Models/BL/BL.py");// 执行py文件 TODO 绝对路径
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FileReader fr = null;
            fr = new FileReader("/root/Models/Data/output.txt"); // 内容是：abc
            BufferedReader br = new BufferedReader(fr);
            ArrayList<Suggestion> suggestions = new ArrayList<>();
            String fundName = "";
            Double weight = 1D;
            String line = "";
            while ((line = br.readLine()) != null) {
                fundName = line;
                line = br.readLine();
                weight = Double.parseDouble(line);
                suggestions.add(new Suggestion(fundName, weight));
            }
            br.close();
            fr.close();
            return suggestions;
            //用输入输出流来截取结果
            //    Scanner sc = new Scanner(new InputStreamReader(proc.getInputStream()));
            //    ArrayList<Suggestion> suggestions = new ArrayList<>();
            //    String fundName;
            //    Double weight;
            //    while (sc.hasNext()) {
            //        fundName = sc.next();
            //        weight = sc.nextDouble();
            //        suggestions.add(new Suggestion(fundName, weight));
            //    }
            //    proc.waitFor();
            //    return suggestions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double cal_lambda() {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python /root/Models/aversion.py");// 执行py文件 TODO 绝对路径
            //用输入输出流来截取结果
            Scanner sc = new Scanner(new InputStreamReader(proc.getInputStream()));
            Double lambda = sc.nextDouble();
            proc.waitFor();
            return lambda;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double cal_glidepath(double alpha, double beta, int retire_year, double c, String gender, double M, double s, double miu, double lmd, double VL1, double VL2, double u1, double u2, double sigma1, double sigma2) {
//		:param alpha: 惩罚参数，问卷得到
//		:param beta: 主观跨期折现因子，问卷得到
//		:param miu: 低风险投资收益率
//		:param lmd: 高风险投资收益率
//		:param retire_year: 退休年份，用户自定义
//		:param c: 缴费率，用户自定义
//		:param gender: 用户性别（与计算寿命有关）,"Male" 或 "Female"
//		:param M: 退休年龄
//		:param s: 养老金替代率，用户自定义
//		:param VL1: 高风险投资 产品长期波动率
//		:param u1: 高风险投资 前一天收益率相对于K天前的变化率，取K=1
//		:param sigma1: 高风险投资 前一天更新的收益率波动率
//		:param VL2: 低风险投资 产品长期波动率
//		:param u2: 低风险投资 前一天收益率相对于K天前的变化率，取K=1
//		:param sigma2: 低风险投资 前一天更新的收益率波动率
//		:return: 当前一年的高风险投资占比
        Process proc;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("C:\\Users\\37756\\Desktop\\test\\args_demo.json"), "UTF-8");

//			JSONObject obj = new JSONObject();//创建JSONObject对象

            osw.write("{");
            osw.write("\"alpha\":" + alpha + ",");
            osw.write("\"beta\":" + beta + ",");
            osw.write("\"miu\":" + miu + ",");
            osw.write("\"lmd\":" + lmd + ",");
            osw.write("\"retire_year\":" + retire_year + ",");
            osw.write("\"c\":" + c + ",");
            osw.write("\"gender\":" + '\"' + gender + '\"' + ",");
            osw.write("\"M\":" + M + ",");
            osw.write("\"s\":" + s + ",");
            osw.write("\"VL1\":" + VL1 + ",");
            osw.write("\"u1\":" + u1 + ",");
            osw.write("\"sigma1\":" + sigma1 + ",");
            osw.write("\"VL2\":" + VL2 + ",");
            osw.write("\"u2\":" + u2 + ",");
            osw.write("\"sigma2\":" + sigma2);
            osw.write("}");

//			System.out.println(obj.toString());

            osw.flush();//清空缓冲区，强制输出数据
            osw.close();//关闭输出流

            proc = Runtime.getRuntime().exec("python /root/Models/glidepath/glidepath.py");// 执行py文件 TODO 绝对路径
            //用输入输出流来截取结果
            Scanner sc = new Scanner(new InputStreamReader(proc.getInputStream()));
            Double y = sc.nextDouble();
            proc.waitFor();
            return y;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double cal_glidepath(double alpha, double beta, int retire_year, double c, String gender, double M, double s) {
//		:param alpha: 惩罚参数，问卷得到
//		:param beta: 主观跨期折现因子，问卷得到
//		:param miu: 低风险投资收益率
//		:param lmd: 高风险投资收益率
//		:param retire_year: 退休年份，用户自定义
//		:param c: 缴费率，用户自定义
//		:param gender: 用户性别（与计算寿命有关）,"Male" 或 "Female"
//		:param M: 退休年龄
//		:param s: 养老金替代率，用户自定义
//		:param VL1: 高风险投资 产品长期波动率
//		:param u1: 高风险投资 前一天收益率相对于K天前的变化率，取K=1
//		:param sigma1: 高风险投资 前一天更新的收益率波动率
//		:param VL2: 低风险投资 产品长期波动率
//		:param u2: 低风险投资 前一天收益率相对于K天前的变化率，取K=1
//		:param sigma2: 低风险投资 前一天更新的收益率波动率
//		:return: 当前一年的高风险投资占比
        double miu = 0.25, lmd = 0.4;
        double VL1 = 0, VL2 = 0;
        double u1 = 0.1, u2 = 0.01;
        double sigma1 = 0.1, sigma2 = 0.01;
        Process proc;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/root/Models/glidepath/args.json"), "UTF-8");

//			JSONObject obj = new JSONObject();//创建JSONObject对象

            osw.write("{");
            osw.write("\"alpha\":" + alpha + ",");
            osw.write("\"beta\":" + beta + ",");
            osw.write("\"miu\":" + miu + ",");
            osw.write("\"lmd\":" + lmd + ",");
            osw.write("\"retire_year\":" + retire_year + ",");
            osw.write("\"c\":" + c + ",");
            osw.write("\"gender\":" + '\"' + gender + '\"' + ",");
            osw.write("\"M\":" + M + ",");
            osw.write("\"s\":" + s + ",");
            osw.write("\"VL1\":" + VL1 + ",");
            osw.write("\"u1\":" + u1 + ",");
            osw.write("\"sigma1\":" + sigma1 + ",");
            osw.write("\"VL2\":" + VL2 + ",");
            osw.write("\"u2\":" + u2 + ",");
            osw.write("\"sigma2\":" + sigma2);
            osw.write("}");

//			System.out.println(obj.toString());

            osw.flush();//清空缓冲区，强制输出数据
            osw.close();//关闭输出流

            proc = Runtime.getRuntime().exec("python /root/Models/glidepath/glidepath.py");// 执行py文件 TODO 绝对路径
            //用输入输出流来截取结果
            Scanner sc = new Scanner(new InputStreamReader(proc.getInputStream()));
            Double y = sc.nextDouble();
            proc.waitFor();
            return y;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Double> getGlidePath(double alpha, double beta, int retire_year, double c, String gender, double M, double s) {
        double miu = 0.25, lmd = 0.4;
        double VL1 = 0, VL2 = 0;
        double u1 = 0.1, u2 = 0.01;
        double sigma1 = 0.1, sigma2 = 0.01;
        Process proc;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/root/Models/glidepath/args.json"), "UTF-8");

//			JSONObject obj = new JSONObject();//创建JSONObject对象

            osw.write("{");
            osw.write("\"alpha\":" + alpha + ",");
            osw.write("\"beta\":" + beta + ",");
            osw.write("\"miu\":" + miu + ",");
            osw.write("\"lmd\":" + lmd + ",");
            osw.write("\"retire_year\":" + retire_year + ",");
            osw.write("\"c\":" + c + ",");
            osw.write("\"gender\":" + '\"' + gender + '\"' + ",");
            osw.write("\"M\":" + M + ",");
            osw.write("\"s\":" + s + ",");
            osw.write("\"VL1\":" + VL1 + ",");
            osw.write("\"u1\":" + u1 + ",");
            osw.write("\"sigma1\":" + sigma1 + ",");
            osw.write("\"VL2\":" + VL2 + ",");
            osw.write("\"u2\":" + u2 + ",");
            osw.write("\"sigma2\":" + sigma2);
            osw.write("}");

//			System.out.println(obj.toString());

            osw.flush();//清空缓冲区，强制输出数据
            osw.close();//关闭输出流

            proc = Runtime.getRuntime().exec("python /root/Models/glidepath/glidepath2.py");// 执行py文件 TODO 绝对路径
            //用输入输出流来截取结果
            Scanner sc = new Scanner(new InputStreamReader(proc.getInputStream()));
            ArrayList<Double> res = new ArrayList<Double>();
            while (sc.hasNext()) {
                Double y = sc.nextDouble();
                res.add(y);
            }
            proc.waitFor();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}