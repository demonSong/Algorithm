package com.daimens.algorithm.november;

import java.util.HashMap;
import java.util.Map;

public class SolutionDay26_L0503 {
	
	
	public int evaluate(String expression) {
		Map<String, Integer> kv = new HashMap<>();
		return evaluate(expression, kv);
    }
	
	public int evaluate(String expression, Map<String, Integer> kv) {
		if (expression.charAt(0) == '(') {
			String nstr = expression.substring(1, expression.length() - 1);
			String[] data = nstr.split(" ");
			if (data[0].equals("let")) {
				int n = nstr.length();
				int j = 4;
				char[] cs = nstr.toCharArray();
				while (j < n) {
					
					int num = 0;
					if (cs[j] == '(') {
						int lf = 1;
						int rt = 0;
						int i = j + 1;
						while (i < n) {
							if (cs[i] == '(') lf ++;
							if (cs[i] == ')') {
								rt ++;
								if (lf == rt) {
									break;
								}
							}
							i++;
						}
						String exp = nstr.substring(j, i + 1);
						Map<String, Integer> clone = new HashMap<>();
						for (String key : kv.keySet()) clone.put(key, kv.get(key));
						return evaluate(exp, clone);
					}
					else if (Character.isDigit(cs[j]) || cs[j] == '-'){
						boolean neg = cs[j] == '-';
						if (cs[j] == '-') j ++;
						while (j < n && cs[j] != ' ') {
							num = num * 10 + cs[j] - '0';
							j ++;
						}
						return neg ? -num : num;
					}
					
					// 变量
					StringBuilder sb = new StringBuilder();
					while (j < n && cs[j] != ' ') {
						sb.append(cs[j]);
						j ++;
					}
					String v = sb.toString();
					j = j + 1;
					if (j >= n) {
						Map<String, Integer> clone = new HashMap<>();
						for (String key : kv.keySet()) clone.put(key, kv.get(key));
						return evaluate(v, clone);
					}
					// 值 or 表达式
					if (cs[j] == '(') {
						int lf = 1;
						int rt = 0;
						int i = j + 1;
						while (i < n) {
							if (cs[i] == '(') lf ++;
							if (cs[i] == ')') {
								rt ++;
								if (lf == rt) {
									break;
								}
							}
							i++;
						}
						String exp = nstr.substring(j, i + 1);
						Map<String, Integer> clone = new HashMap<>();
						for (String key : kv.keySet()) clone.put(key, kv.get(key));
						num = evaluate(exp, clone);
						j = i + 2;
					}
					else if (cs[j] != '-' && !Character.isDigit(cs[j])) {
						sb = new StringBuilder();
						while (j < n && cs[j] != ' ') {
							sb.append(cs[j]);
							j ++;
						}
						
						Map<String, Integer> clone = new HashMap<>();
						for (String key : kv.keySet()) clone.put(key, kv.get(key));
						num = evaluate(sb.toString(), clone);
						j ++;
					}
					else {
						boolean neg = cs[j] == '-';
						if (cs[j] == '-') j ++;
						while (j < n && cs[j] != ' ') {
							num = num * 10 + cs[j] - '0';
							j ++;
						}
						if (neg) num = -num;
						j++;
					}
					
					kv.put(v, num);
				}
				
				return 0;
			}
			else if (data[0].equals("add")) {
				Map<String, Integer> clone1 = new HashMap<>();
				for (String key : kv.keySet()) clone1.put(key, kv.get(key));

				Map<String, Integer> clone2 = new HashMap<>();
				for (String key : kv.keySet()) clone2.put(key, kv.get(key));
				return evaluate(splitAdd(nstr)[0], clone1) + evaluate(splitAdd(nstr)[1], clone2);
			}
			else {
				Map<String, Integer> clone1 = new HashMap<>();
				for (String key : kv.keySet()) clone1.put(key, kv.get(key));

				Map<String, Integer> clone2 = new HashMap<>();
				for (String key : kv.keySet()) clone2.put(key, kv.get(key));
				return evaluate(splitMult(nstr)[0], clone1) * evaluate(splitMult(nstr)[1], clone2);
			}
		}
		else {
			if (Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '-') {
				return Integer.parseInt(expression);
			}
			else { // 变量
				return kv.get(expression);
			}
		}
	}
	
	String[] splitAdd(String expression) {
		int n = expression.length();
		String[] data = expression.split(" ");
		String[] ans = new String[2];
		if (expression.charAt(4) == '(') {
			int lf = 1;
			int rt = 0;
			int j = 5;
			for (; j < n; ++j) {
				if (expression.charAt(j) == '(') lf ++;
				if (expression.charAt(j) == ')') {
					rt ++;
					if (lf == rt) {
						break;
					}
				}
			}
			ans[0] = expression.substring(4, j + 1);
		}
		else {
			ans[0] = data[1];
		}
		
		if (expression.charAt(n - 1) == ')') {
			int rt = 1;
			int lf = 0;
			int j = n - 2;
			for (; j >= 0; --j) {
				if (expression.charAt(j) == ')') rt ++;
				if (expression.charAt(j) == '(') {
					lf ++;
					if (lf == rt) {
						break;
					}
				}
			}
			ans[1] = expression.substring(j, n);
		}
		else {
			ans[1] = data[data.length - 1];
		}
		
		return ans;
	}
	
	String[] splitMult(String expression) {
		int n = expression.length();
		String[] data = expression.split(" ");
		String[] ans = new String[2];
		if (expression.charAt(5) == '(') {
			int lf = 1;
			int rt = 0;
			int j = 6;
			for (; j < n; ++j) {
				if (expression.charAt(j) == '(') lf ++;
				if (expression.charAt(j) == ')') {
					rt ++;
					if (lf == rt) {
						break;
					}
				}
			}
			ans[0] = expression.substring(5, j + 1);
		}
		else {
			ans[0] = data[1];
		}
		
		if (expression.charAt(n - 1) == ')') {
			int rt = 1;
			int lf = 0;
			int j = n - 2;
			for (; j >= 0; --j) {
				if (expression.charAt(j) == ')') rt ++;
				if (expression.charAt(j) == '(') {
					lf ++;
					if (lf == rt) {
						break;
					}
				}
			}
			ans[1] = expression.substring(j, n);
		}
		else {
			ans[1] = data[data.length - 1];
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay26_L0503 day = new SolutionDay26_L0503();
		String expression = "(let x -2 y x y)";
		System.out.println(day.evaluate(expression));
	}
}
