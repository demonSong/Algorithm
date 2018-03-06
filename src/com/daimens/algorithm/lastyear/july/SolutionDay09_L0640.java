package com.daimens.algorithm.july;

public class SolutionDay09_L0640 {

	/**
	 * 正则表达式：
	 * 
	 * 执行正向预测先行搜索的子表达式，该表达式匹配处于匹配 pattern
	 * 的字符串的起始点的字符串。它是一个非捕获匹配，即不能捕获供以后使用的匹配。例如，'Windows (?=95|98|NT|2000)'
	 * 匹配"Windows 2000"中的"Windows"，但不匹配"Windows
	 * 3.1"中的"Windows"。预测先行不占用字符，即发生匹配后，下一匹配的搜索紧随上一匹配之后，而不是在组成预测先行的字符后。
	 * 
	 * @param equation
	 * @return
	 */
//	public String solveEquation(String equation) {
//		int[] res1 = parse(equation.split("=")[0]);
//		int[] res2 = parse(equation.split("=")[1]);
//		int x = res1[0] - res2[0];
//		int n = res2[1] - res1[1];
//		if (x == 0 && n == 0) return "Infinite solutions";
//		if (x == 0 && n != 0) return "No solution";
//		
//		return "x=" + n / x;
//	}
//		
//	public int[] parse(String express){
//		String[] tokens = express.split("(?=[-+])");
//		int[] res = new int[2];	
//		for (String token : tokens){
//			if (token.equals("-x")) res[0] -= 1;
//			else if (token.equals("+x") || token.equals("x")) res[0] += 1;
//			else if (token.endsWith("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
//			else res[1] += Integer.parseInt(token);
//		}
//		return res;
//	}
	
//	public String solveEquation(String equation) {
//		String[] values = equation.split("=");
//		values[0] = (values[0].charAt(0) != '-' ? "+" : "") + values[0];
//		values[1] = (values[1].charAt(0) != '-' ? "+" : "") + values[1];
//		// values[0] = values[0].replace("+", " +").replace("-", " -");
//		// values[1] = values[1].replace("+", " +").replace("-", " -");
//
//		long sumX = 0;
//		long sum = 0;
//		String[] nums = values[0].split("(?=[-+])");
//		for (int i = 0; i < nums.length; ++i) {
//			String num = nums[i];
//			if (num.isEmpty())
//				continue;
//			if (num.contains("x")) {
//				int flag = num.substring(0, 1).equals("+") ? 1 : -1;
//				sumX += (flag * Integer.parseInt(
//						(num.substring(1, num.length() - 1)).isEmpty() ? "1" : (num.substring(1, num.length() - 1))));
//			} else {
//				int flag = num.substring(0, 1).equals("+") ? 1 : -1;
//				sum += (flag * Integer.parseInt(num.substring(1, num.length())));
//			}
//		}
//
//		nums = values[1].split("\\ ");
//		for (int i = 0; i < nums.length; ++i) {
//			String num = nums[i];
//			if (num.isEmpty())
//				continue;
//			if (num.contains("x")) {
//				int flag = num.substring(0, 1).equals("+") ? -1 : 1;
//				sumX += (flag * Integer.parseInt(
//						(num.substring(1, num.length() - 1)).isEmpty() ? "1" : (num.substring(1, num.length() - 1))));
//			} else {
//				int flag = num.substring(0, 1).equals("+") ? -1 : 1;
//				sum += (flag * Integer.parseInt(num.substring(1, num.length())));
//			}
//		}
//
//		if (sumX == 0 && sum == 0)
//			return "Infinite solutions";
//		if (sumX == 0 && sum != 0)
//			return "No solution";
//		return "x=" + (sum / (-sumX));
//	}
//	
	
	public String solveEquation(String equation) {
		String[] express = equation.split("=");
		int[] res1= parse(transform(express[0]).toCharArray());
		int[] res2 = parse(transform(express[1]).toCharArray());
		
		int x = res1[0] - res2[0];
		int n = res2[1] - res1[1];
		if (x == 0 && n == 0) return "Infinite solutions";
		if (x == 0 && n != 0) return "No solution";
		
		return "x=" + n / x;
	}
	
	public String transform(String express){
		return express + "+";
	}
	
	public int[] parse(char[] express){
		int[] res = new int[2];
		
		int flag = express[0] == '-' ? -1 : 1;
		int i = express[0] == '-' ? 1 : 0;
		StringBuilder sb = new StringBuilder();
		
		for (; i < express.length; ++i){
			if (express[i] == '-' || express[i] == '+'){
				String token = sb.toString();
				if (token.equals("x")) res[0] += (flag * 1);
				else if (token.endsWith("x")) res[0] += (flag * Integer.parseInt(token.substring(0, token.indexOf("x"))));
				else res[1] += (flag * Integer.parseInt(token));
				sb = new StringBuilder();
				flag = express[i] == '-' ? -1 : 1;
			}
			else{
				sb.append(express[i]);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		SolutionDay09_L0640 day = new SolutionDay09_L0640();
		System.out.println(day.solveEquation("x+5-3+x=6+x-2"));
	}

}
