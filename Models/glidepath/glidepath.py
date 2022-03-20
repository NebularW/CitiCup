# 下滑轨道
import math
import datetime
import csv
import os
import json


def mgf(h, miu, sigma):
    return math.exp(miu * h + sigma * sigma / 2 * h * h)


def calc_P_and_Q(N, c, F, miu, lmd, sigma1, sigma2, alpha, beta, theta) -> (list, list, float):
    P = [0] * (N + 1)
    Q = [0] * (N + 1)

    def g_miu(h):
        return mgf(h, miu, sigma1)

    def g_lambda(h):
        return mgf(h, lmd, sigma2)

    def g_miu_lambda(h, k):
        return g_miu(h) + g_lambda(k)

    g1 = g_miu(2) + g_lambda(2) - g_miu_lambda(1, 1)
    g2 = g_miu(2) * g_lambda(2) - math.pow(g_miu_lambda(1, 1), 2)
    g3 = g_miu(1) * g_lambda(2) + g_miu(2) * g_lambda(1) - g_miu_lambda(1, 1) * (g_miu(1) + g_lambda(1))

    P[N] = theta
    Q[N] = theta * (F[N] + alpha / 2.0)
    for t in reversed(range(0, N)):
        P[t] = 1 + beta * g2 / g1 * P[t + 1]
        Q[t] = F[t] + alpha / 2.0 - beta / g1 * (c * g2 * P[t + 1] - g3 * Q[t + 1])

    return P, Q, g1


def calc_C(f, F, N, alpha, theta) -> list:
    """
    :param f: f[t]表示t时刻基金净值
    :param N: 缴费停止时间（退休）
    :param alpha: 惩罚参数 α，本文实际上是在考虑具有不同风险厌恶因子的效用函 数，因此本文考虑的是具有不同风险特征的个体
    :param theta: 权重参数 θ
    :return:  C[t]表示t时刻的损失
    """
    C = [0] * (N + 1)
    for t in range(1, N):
        """C[t] = (F[t] - f[t]) ^ 2 + α (F[t] - f[t])，t= 1,...,N - 1"""
        C[t] = (F[t] - f[t]) * (F[t] - f[t]) + alpha * (F[t] - f[t])
    C[N] = theta * (F[N] - f[N]) * (F[N] - f[N]) + alpha * (F[N] - f[N])
    return C


def calc_G(C, N, beta) -> float:
    """
    :param C: C[t]表示在 t 时刻产生的损失
    :param N: 缴费停止时间（退休）
    :param beta: 主观的跨期折现因子
    :return:
    """
    G = [0] * (N + 1)
    for t in range(0, N + 1):
        for s in range(t, N + 1):
            G[t] += pow(beta, s - t) * C[s]

    return G


def calc_d(gender: str, filename="sheet.csv") -> list:
    """
    :param gender: 性别，Male or Female
    :param filename: csv文件名，默认sheet.csv
    :return:
    """
    d_male = [0.000566, 0.000386, 0.000268, 0.000196, 0.000158, 0.000141, 0.000132, 0.000129, 0.000131, 0.000137,
              0.000146, 0.000157, 0.00017, 0.000184, 0.000197, 0.000208, 0.000219, 0.000227, 0.000235, 0.000241,
              0.000248, 0.000256, 0.000264, 0.000273, 0.000284, 0.000297, 0.000314, 0.000333, 0.000354, 0.000379,
              0.000407, 0.000438, 0.000472, 0.000509, 0.000549, 0.000592, 0.000639, 0.00069, 0.000746, 0.000808,
              0.000878, 0.000955, 0.001041, 0.001138, 0.001245, 0.001364, 0.001496, 0.001641, 0.001798, 0.001967,
              0.002148, 0.00234, 0.002544, 0.002759, 0.002985, 0.003221, 0.003469, 0.003731, 0.004014, 0.004323,
              0.00466, 0.005034, 0.005448, 0.005909, 0.006422, 0.006988, 0.00761, 0.008292, 0.009046, 0.009897,
              0.010888, 0.01208, 0.01355, 0.015387, 0.017686, 0.020539, 0.024017, 0.028162, 0.032978, 0.038437,
              0.044492, 0.051086, 0.058173, 0.065722, 0.073729, 0.082223, 0.091239, 0.1009, 0.111321, 0.122608, 0.13487,
              0.148212, 0.162742, 0.178566, 0.195793, 0.214499, 0.23465, 0.25618, 0.279025, 0.30312, 0.328401, 0.354803,
              0.382261, 0.41071, 0.440086, 1.0]
    d_female = [0.000453, 0.000289, 0.000184, 0.000124, 9.5e-05, 8.4e-05, 7.8e-05, 7.4e-05, 7.2e-05, 7.2e-05, 7.4e-05,
                7.7e-05, 8e-05, 8.5e-05, 9e-05, 9.5e-05, 0.0001, 0.000105, 0.00011, 0.000115, 0.00012, 0.000125,
                0.000129, 0.000134, 0.000139, 0.000144, 0.000149, 0.000154, 0.00016, 0.000167, 0.000175, 0.000186,
                0.000198, 0.000213, 0.000231, 0.000253, 0.000277, 0.000305, 0.000337, 0.000372, 0.00041, 0.00045,
                0.000494, 0.00054, 0.000589, 0.00064, 0.000693, 0.00075, 0.000811, 0.000877, 0.00095, 0.001031, 0.00112,
                0.001219, 0.001329, 0.00145, 0.001585, 0.001736, 0.001905, 0.002097, 0.002315, 0.002561, 0.002836,
                0.003137, 0.003468, 0.003835, 0.004254, 0.00474, 0.005302, 0.005943, 0.00666, 0.00746, 0.008369,
                0.009436, 0.01073, 0.012332, 0.014315, 0.016734, 0.019619, 0.022971, 0.02677, 0.030989, 0.035598,
                0.040576, 0.045915, 0.051616, 0.057646, 0.064084, 0.070942, 0.078241, 0.086003, 0.094249, 0.103002,
                0.112281, 0.122109, 0.13254, 0.143757, 0.155979, 0.169421, 0.184301, 0.200836, 0.219242, 0.239737,
                0.262537, 0.287859, 1.0]

    if (gender == "Male"):
        return d_male
    else:
        return d_female

    # csv_file_reader = csv.reader(open(filename, 'r', encoding="utf-8"))
    #
    # d = []
    # for line in csv_file_reader:
    #     try:
    #         age = int(line[0])
    #     except ValueError:
    #         continue
    #     else:
    #         d.append(float(line[5 if gender == "Male" else 6]))
    #
    # return d


def calc_F(c, s, d, r, r_star, N, M: int, maxSpan: int = 105) -> list:
    """
    :param s: 养老金替代率
    :param d: 为经验生命表中各年龄死亡率，可参照《中国人身保险业 经验生命表( 2010 ~ 2013) 》提供的养老类业务死亡率数据
    :param r: 为折现率,选择一年期定期存款利率，令 r = 1. 75%。
    :param r_star: r* 取值介于低风险资产收益率和高风险资产收益率之 间，这里令它等于低风险资产收益均值μ
    :param N:
    :param M: 为退休时的投资者年龄。
    :param maxSpan: 死亡率为1的年龄，取120
    :return: 预期目标
    """
    F = [0] * (N + 1)
    # calculate F[N]
    # M = int(M)
    for t in range(M, maxSpan + 1):
        factor = 1
        for i in range(M, t + 1):
            factor *= 1 - d[i]
        F[N] += s * math.exp(-r * (t - M)) * factor

    for t in reversed(range(0, N)):
        F[t] = F[t + 1] * math.exp(-r_star) - c

    return F


def calc_y_and_f(N, c, P, Q, miu, lmd, sigma1, sigma2, g1) -> (list, list):
    f = [0] * (N + 1)
    y = [0] * (N + 1)

    def g_miu(h):
        return mgf(h, miu, sigma1)

    def g_lambda(h):
        return mgf(h, lmd, sigma2)

    f[0] = 0
    y[0] = Q[1] * (g_miu(1) - g_lambda(1)) / (P[1] * (f[0] + c) * g1)
    for t in range(1, N):
        y[t] = Q[t + 1] * (g_miu(1) - g_lambda(1)) / (P[t + 1] * (f[t] + c) * g1)
        f[t + 1] = (f[t] + c) * ((1 - y[t]) * math.exp(miu * t) + y[t] * math.exp(lmd * t))

    return y, f


def calc_N(retire_year) -> int:
    """
    :param retire_year: 退休时间（年）
    :return: 缴费时长（年）
    """
    cur_year = datetime.datetime.now().year
    N = retire_year - cur_year
    return N


def calc_sigma(VL: float, u: float, sigma: float, alpha: float = 0.02, beta: float = 0.96,
               gamma: float = 0.02) -> float:
    """
    Garch模型
    :param VL: 长期波动率
    :param u: 前一天收益率相对于K天前的变化率，取K=1
    :param sigma: 前一天更新的收益率波动率
    :param alpha: 预设0.02
    :param beta: 预设0.96
    :param gamma: 预设0.02
    :return:
    """
    return math.sqrt(gamma * VL + alpha * u * u + beta * sigma * sigma)


def calc_y(alpha: float, beta: float, miu: float, lmd: float, retire_year: int, gender: str, M: int, s: float, c: float,
           VL1: float, VL2: float, u1: float, u2: float, sigma1: float, sigma2: float) -> float:
    """
    :param alpha: 惩罚参数，问卷得到
    :param beta: 主观跨期折现因子，问卷得到
    :param miu: 低风险投资收益率
    :param lmd: 高风险投资收益率
    :param retire_year: 退休年份，用户自定义
    :param gender: 用户性别（与计算寿命有关）
    :param M: 退休年龄
    :param s: 养老金替代率，用户自定义
    :param c: 缴费率，用户自定义
    :param VL1: 高风险投资 产品长期波动率
    :param u1: 高风险投资 前一天收益率相对于K天前的变化率，取K=1
    :param sigma1: 高风险投资 前一天更新的收益率波动率
    :param VL2: 低风险投资 产品长期波动率
    :param u2: 低风险投资 前一天收益率相对于K天前的变化率，取K=1
    :param sigma2: 低风险投资 前一天更新的收益率波动率
    :return: 当前一年的高风险投资占比
    """

    # σ1 低风险投资风险，输入产品参数
    sigma1 = calc_sigma(VL1, u1, sigma1)
    # σ2 高风险投资风险，输入产品参数
    sigma2 = calc_sigma(VL2, u2, sigma2)

    # θ 损失权重
    theta = 2
    # d[t]经验死亡率，根据性别读表得到
    d = calc_d(gender)
    # N缴费年限=退休年份-当前年份
    N = calc_N(retire_year)
    # r: 为折现率，选择一年期定期存款利率，令 r = 1. 75%
    r = 0.0175
    # r_star: r* 取值介于低风险资产收益率和高风险资产收益率之 间，这里令它等于低风险资产收益均值μ
    r_star = miu

    F = calc_F(c, s, d, r, r_star, N, M)
    P, Q, g1 = calc_P_and_Q(N, c, F, miu, lmd, sigma1, sigma2, alpha, beta, theta)

    y, f = calc_y_and_f(N, c, P, Q, miu, lmd, sigma1, sigma2, g1)

    C = calc_C(f, F, N, alpha, theta)
    G = calc_G(C, N, beta)

    return y[1]


def args_reader():
    curPath = os.path.abspath(__file__)
    curDir = os.path.dirname(curPath)
    args_path = curDir + "/args.json"
    args_dict = json.load(open(args_path))
    # print(args_dict)

    # if type(args_dict["M"]) == type(1.0):
    #     a = 5 / 0

    return args_dict["alpha"], args_dict["beta"], args_dict["miu"], args_dict["lmd"], args_dict["sigma1"], args_dict[
        "sigma2"], args_dict["retire_year"], args_dict["c"], args_dict["gender"], args_dict["M"], args_dict["s"], \
           args_dict["VL1"], args_dict["VL2"], args_dict["u1"], args_dict["u2"]


if __name__ == "__main__":
    alpha, beta, miu, lmd, sigma1, sigma2, retire_year, c, gender, M, s, VL1, VL2, u1, u2 = args_reader()

    # # α 惩罚参数，由问卷得到
    # alpha = 2
    #
    # # β 主观跨期折现因子，由问卷得到
    # beta = 0.95
    #
    # # μ 低风险投资收益率，输入产品参数
    # miu = 0.02
    #
    # # λ 高风险投资收益率，输入产品参数
    # lmd = 0.04
    #
    # # retire_year 退休年份，用户自定义
    # retire_year = 2045
    #
    # # c缴费率，用户自定义
    # c = 0.2
    #
    # # gender 性别，"Male" or "Female"
    # gender = "Male"
    #
    # # M 退休时投资者年龄，用户自定义
    # M = 65
    #
    # # s为养老金替代率，建议值0.7，可自定义
    # s = 0.7

    # VL: 长期波动率
    # u: 前一天收益率相对于K天前的变化率，取K=1
    # sigma: 前一天更新的收益率波动率
    res = calc_y(alpha, beta, miu, lmd, retire_year, gender, M, s, c, VL1, VL2, u1, u2, sigma1, sigma2)
    print(res)
