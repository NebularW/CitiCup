public class ModelService {
	public ArrayList<Suggestion> BL_calcu_w() {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("python /root/Models/glidepath/glidepath.py");// 执行py文件 TODO 绝对路径
			//用输入输出流来截取结果
			Scanner sc = new Scanner(new InputStreamReader(proc.getInputStream()));
			ArrayList<Suggestion> suggestions = new ArrayList<>();
			String fundName;
			Double weight;
			while (sc.hasNext()) {
				fundName = sc.nextLine();
				weight = sc.nextDouble();
				suggestions.add(new Suggestion(fundName, weight));
			}
			proc.waitFor();
			return suggestions;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Double cal_lambda() {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("python D:\\demo.py");// 执行py文件 TODO 绝对路径
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
	
	public static void main(String[] args) {
		cal_glidepath();
	}
	
	public static Double cal_glidepath(double alpha, double beta, double miu, double lmd, int retire_year, double c, String gender, int M, double s, double VL1, double VL2, double u1, double u2, double sigma1, double sigma2) {
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
}