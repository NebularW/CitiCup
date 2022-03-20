import math


def calc_sigma(u: list, VL, alpha: float, beta: float, gamma: float) -> list:
	sigma = [0.0 for i in range(n + 1)]
	for i in range(1, n):
		sigma[i] = math.sqrt(gamma * VL + alpha * u[i - 1] * u[i - 1] + beta * sigma[i - 1] * sigma[i - 1])


if __name__ == "__main__":
	# 需要计算的
	n = 365
	# u_(n - 1) 是前一天相对于K天的变化率
	u = [0.0 for i in range(n + 1)]

	alpha = 0.02
	beta = 0.96
	gamma = 0.02

	# V_L长期波动率
	VL = 0

	sigma = calc_sigma(u, VL, alpha, beta, gamma)
