import numpy as np
from scipy.optimize import minimize


def objective(x, n, other_value, value, sign=-1.0):
    N = len(other_value[0])
    resSum = 0
    for i in range(n):
        res = 0
        for j in range(N):
            res += x[j] * other_value[i][j]
        res /= value[i]
        res -= 1
        resSum += res
    return resSum


def constraint(x):
    return np.sum(x) - 1.0


def positive_constraint(x):
    return x


def cal_lamda(n, other, value):
    x0 = np.ones(n)
    cons = ({'type': 'eq', 'fun': constraint},
            {'type': 'ineq', 'fun': positive_constraint})
    res = minimize(objective, x0, args=[n, other, value], method="SLSQP", constraints=cons)
    w = res.x
    obj = objective(w, n, other, value, sign=1.0)
    return 1 / (1 + obj / n)
