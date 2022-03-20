# 协方差矩阵和收益率向量
import numpy as np
from scipy.optimize import minimize


# def getCov(R: np.matrix):
#     mean = np.mean(R)
#     length = len(R)
#     cov = np.ones((length, length), dtype=float)
#     for i in range(length):
#         for j in range(length):
#             cov[i][j] = (R[i] - mean) * (R[j] - mean) / (length - 1)
#     return cov


# 风险预算优化
def calculate_portfolio_var(w, V):
    # 计算组合风险的函数
    w = np.matrix(w)
    return (w * V * w.T)[0, 0]


def calculate_risk_contribution(w, V):
    # 计算单个资产对总体风险贡献度的函数
    w = np.matrix(w)
    sigma = np.sqrt(calculate_portfolio_var(w, V))
    # 边际风险贡献
    MRC = V * w.T
    # 风险贡献
    RC = np.multiply(MRC, w.T) / sigma
    return RC


def risk_budget_objective(x, pars):
    # 计算组合风险
    V = pars[0]  # 协方差矩阵
    x_t = pars[1]  # 组合中资产预期风险贡献度的目标向量
    sig_p = np.sqrt(calculate_portfolio_var(x, V))  # portfolio sigma
    risk_target = np.asmatrix(np.multiply(sig_p, x_t))
    asset_RC = calculate_risk_contribution(x, V)
    J = sum(np.square(asset_RC - risk_target.T))[0, 0]  # sum of squared error
    return J


def total_weight_constraint(x):
    return np.sum(x) - 1.0


def long_only_constraint(x):
    return x


# [[0.19542346 0.21540383 0.16243748 0.42673523]]

# 根据资产预期目标风险贡献度来计算各资产的权重
def calcu_w(x, V):
    w0 = np.ones(5)
    #     x_t = [0.25, 0.25, 0.25, 0.25] # 目标是让四个资产风险贡献度相等，即都为25%
    x_t = x
    cons = ({'type': 'eq', 'fun': total_weight_constraint},
            {'type': 'ineq', 'fun': long_only_constraint},
            )
    res = minimize(risk_budget_objective, w0, args=[V, x_t], method='SLSQP', constraints=cons,
                   options={'disp': True})
    w_rb = np.asmatrix(res.x)
    return w_rb


# # 将各资产风险贡献度绘制成柱状图
# def plot_rc(w):
#     rc = calculate_risk_contribution(w, V)
#     rc = rc.tolist()
#     rc = [i[0] for i in rc]
#     rc = pd.DataFrame(rc, columns=['rick contribution'], index=[1, 2, 3, 4])
#     T.plot(rc, chart_type='column', title='Contribution to risk')


# 假设四个资产的风险贡献度相等
# w_rb = calcu_w([0.25, 0.25, 0.25, 0.25])
# print('各资产权重：', w_rb)


if __name__ == "__main__":
    R = np.array([12, 12, 43, 5])

