import numpy as np
from sklearn.linear_model import LinearRegression

A = [[230.1, 37.8, 69.2, 1], [44.5, 39.3, 45.1, 1], [17.2, 45.9, 1, 69.3], [151.5, 41.3, 1, 58.5],
     [180.8, 1, 10.8, 58.4]]
R = np.array([22.1, 10.4, 9.3, 18.5, 12.9])


def cal_avg(*P):
    avg = 0
    for x in P:
        avg += x
    x /= len(P)


def multiple_regression(A, R):
    X = np.array(A, float)
    linreg = LinearRegression()
    reg = LinearRegression().fit(X, R)
    score = reg.score(X, R) #R2
    coef = reg.coef_
    intercept = reg.intercept_  #截距


if __name__ == "__main__":
    multiple_regression(A, R)
