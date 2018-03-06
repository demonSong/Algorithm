package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         8.String to Integer(atoi)
 * 
 *         Implement atoi to convert a string to an integer.
 * 
 *         Hint: Carefully consider all possible input cases. If you want a
 *         challenge, please do not see below and ask yourself what are the
 *         possible input cases.
 * 
 *         Notes: It is intended for this problem to be specified vaguely (ie,
 *         no given input specs). You are responsible to gather all the input
 *         requirements up front.
 *
 */
public class SolutionDay08_L0008 {
	
//	public int myAtoi(String str) {
//		if (str.isEmpty() || !valid(str)) return 0;
//		char[] c = str.toCharArray();
//		int i = 0;
//		while (i < c.length && c[i] == ' ') i++;
//		int flag = 1;
//		if (c[i] == '+' || c[i] == '-'){
//			flag = c[i] == '-' ? -1 : 1;
//		}
////		if (i < c.length && c[i] == '-'){
////			flag = -1;
////			i++;
////		}
////		if (i < c.length && c[i] == '+'){
////			flag = 1;
////			i++;
////		}
//		
//		long val = 0;
//		while (i < c.length && Character.isDigit(c[i])) {
//			if (Integer.MAX_VALUE / 10 < val || Integer.MAX_VALUE / 10 == val && Integer.MAX_VALUE % 10 < (c[i] - '0'))
//				return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//			val = val * 10 + (c[i++] - '0');
//		}
//		val = val * flag;
////		if (val > Integer.MAX_VALUE) return Integer.MAX_VALUE;
////		if (val < Integer.MIN_VALUE) return Integer.MIN_VALUE;
//		return (int)val;
//	}
//	
//	private boolean valid(String str){
//		boolean existMinus = false;
//		boolean existPluss = false;
//		for (int i = 0; i < str.length(); ++i){
//			if (str.charAt(i) == '+') existPluss = true;
//			if (str.charAt(i) == '-') existMinus = true;
//		}
//		
//		return ! (existMinus && existPluss);
//	}
	
	public int myAtoi(String str) {
		if (str.isEmpty()) return 0;
		char[] c = str.toCharArray();
		int i = 0;
		while (i < c.length && c[i] == ' ') i++;
		
		int flag = 1;
		if (c[i] == '+' || c[i] == '-'){
			flag = c[i++] == '-' ? -1 : 1;
		}

		long val = 0;
		while (i < c.length && Character.isDigit(c[i])) {
			if (Integer.MAX_VALUE / 10 < val || Integer.MAX_VALUE / 10 == val && Integer.MAX_VALUE % 10 < (c[i] - '0'))
				return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			val = val * 10 + (c[i++] - '0');
		}
		val = val * flag;
		return (int)val;
	}

	public static void main(String[] args) {
		SolutionDay08_L0008 day = new SolutionDay08_L0008();
		System.out.println(day.myAtoi("-1010023630o4"));
	}
}
