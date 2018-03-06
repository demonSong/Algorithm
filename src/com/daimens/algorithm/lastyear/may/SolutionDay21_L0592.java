package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         592.Fraction Action and Subtraction
 * 
 *         Given a string representing an expression of fraction addition and
 *         subtraction, you need to return the calculation result in string
 *         format. The final result should be irreducible fraction. If your
 *         final result is an integer, say 2, you need to change it to the
 *         format of fraction that has denominator 1. So in this case, 2 should
 *         be converted to 2/1.
 * 
 *         Example 1: Input:"-1/2+1/2" Output: "0/1" Example 2:
 *         Input:"-1/2+1/2+1/3" Output: "1/3" Example 3: Input:"1/3-1/2" Output:
 *         "-1/6" Example 4: Input:"5/3+1/3" Output: "2/1" Note: The input
 *         string only contains '0' to '9', '/', '+' and '-'. So does the
 *         output. Each fraction (input and output) has format
 *         ±numerator/denominator. If the first input fraction or the output is
 *         positive, then '+' will be omitted. The input only contains valid
 *         irreducible fractions, where the numerator and denominator of each
 *         fraction will always be in the range [1,10]. If the denominator is 1,
 *         it means this fraction is actually an integer in a fraction format
 *         defined above. The number of given fractions will be in the range
 *         [1,10]. The numerator and denominator of the final result are
 *         guaranteed to be valid and in the range of 32-bit int.
 *
 */
public class SolutionDay21_L0592 {

	// public String fractionAddition(String expression) {
	// String[] hh = expression.split("/");
	// int[][] num = new int[hh.length-1][2];
	//
	// int[] tmp = new int[2 * (hh.length-1)];
	// opear = new char[hh.length-2];
	// count2 = 0;
	// int count = 0;
	// for (int i = 0; i < hh.length; i++){
	// if (isNumeric(hh[i])){
	// tmp[count++] = Integer.parseInt(hh[i]);
	// }else{
	// String[] tt = split(hh[i]);
	// tmp[count++] = Integer.parseInt(tt[0]);
	// tmp[count++] = Integer.parseInt(tt[1]);
	// }
	// }
	//
	// if (tmp.length == 2){
	// return String.valueOf(tmp[0]) + "/" + String.valueOf(tmp[1]);
	// }
	//
	// if (tmp.length == 4){
	// int time = tmp[1] * tmp[3];
	// int fenzi1 = time / tmp[1] * tmp[0];
	// int fenzi2 = time / tmp[3] * tmp[2];
	//
	// if (opear[0] == '+'){
	// int ans1 = fenzi1 + fenzi2;
	// int zz = ans1 < 0 ? -ans1 : ans1;
	// int yy = maxCommonDivisor(zz, time);
	// ans1 = ans1 / yy;
	// time = time / yy;
	// return String.valueOf(ans1)+"/"+String.valueOf(time);
	// }
	// else{
	// int ans1 = fenzi1 - fenzi2;
	// int zz = ans1 < 0 ? -ans1 : ans1;
	// int yy = maxCommonDivisor(zz, time);
	// ans1 = ans1 / yy;
	// time = time / yy;
	// return String.valueOf(ans1)+"/"+String.valueOf(time);
	// }
	// }
	//
	// int index = -1;
	// char tttt = ' ';
	// for (int i = 0; i < expression.length(); i++){
	// if (expression.charAt(i) == '+' || expression.charAt(i) == '-'){
	// index = i;
	// tttt = expression.charAt(i);
	// }
	// }
	//
	// String left = expression.substring(0, index);
	// String right = expression.substring(index+1);
	//
	// String newans = fractionAddition(left)+new String(new char[]{tttt}) +
	// fractionAddition(right);
	// return fractionAddition(newans);
	//
	// }

	public static int maxCommonDivisor(int m, int n) {
		if (m < n) {// 保证m>n,若m<n,则进行数据交换
			int temp = m;
			m = n;
			n = temp;
		}
		if (m % n == 0) {// 若余数为0,返回最大公约数
			return n;
		} else { // 否则,进行递归,把n赋给m,把余数赋给n
			return maxCommonDivisor(n, m % n);
		}
	}

	char[] opear;
	int count2 = 0;

	private String[] split(String x) {
		char[] c = x.toCharArray();
		int index = -1;
		for (int i = 0; i < x.length(); i++) {
			if (c[i] == '+' || c[i] == '-') {
				index = i;
				opear[count2++] = c[i];
				break;
			}
		}
		String[] ans = new String[2];
		ans[0] = x.substring(0, index);
		ans[1] = x.substring(index + 1);
		return ans;
	}

	public boolean isNumeric(String str) {
		if (str.charAt(0) == '-') {
			return isNumeric(str.substring(1));
		}
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// public String fractionAddition(String expression) {
	// if (noOpeartor(expression)) {
	// return expression;
	// }
	//
	// String ans = "";
	// for (int i = 0; i < expression.length(); i++) {
	// if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
	// if (i == 0) continue;
	// String left = fractionAddition(expression.substring(0, i));
	// String right = fractionAddition(expression.substring(i + 1));
	// int fz1 = Integer.valueOf(left.split("/")[0]);
	// int fz2 = Integer.valueOf(right.split("/")[0]);
	// int fm1 = Integer.valueOf(left.split("/")[1]);
	// int fm2 = Integer.valueOf(right.split("/")[1]);
	//
	// int time = fm1 * fm2;
	// switch (expression.charAt(i)) {
	// case '+': {
	// int fz3 = fz1 * fm2 + fz2 * fm1;
	// int fm3 = time;
	// ans = reduce(fz3, fm3);
	// }
	// break;
	// case '-': {
	// int fz3 = fz1 * fm2 - fz2 * fm1;
	// int fm3 = time;
	// ans = reduce(fz3, fm3);
	// }
	// break;
	// default:
	// break;
	// }
	//
	// return ans;
	// }
	// }
	// return ans;
	// }
	
	private class Fraction {
		long a, b;

		Fraction(long a, long b) {
			if (b < 0) {
				a = -a;
				b = -b;
			}
			long g = gcd(Math.abs(a), Math.abs(b));
			a /= g;
			b /= g;
			this.a = a;
			this.b = b;
		}

		private long gcd(long a, long b) {
			return b == 0 ? a : gcd(b, a % b);
		}

		private Fraction add(Fraction that) {
			return new Fraction(a * that.b + b * that.a, b * that.b);
		}

		@Override
		public String toString() {
			return a + "/" + b;
		}
	}
	
//	public String fractionAddition(String expression) {
//		String[] exps = expression.replace("-", " +-").replace("+", " +").split(" +");
//		Fraction zero = new Fraction(0, 1);
//		for (int i = 0; i < exps.length; i++){
//			if (exps[i].isEmpty()) continue;
//			long a = 0;
//			if(exps[i].charAt(0) == '+') a = Long.parseLong(exps[i].substring(1).split("/")[0]);
//			else a = Long.parseLong(exps[i].split("/")[0]);
//			long b = 0;
//			if(exps[i].charAt(0) == '+') b = Long.parseLong(exps[i].substring(1).split("/")[1]);
//			else b = Long.parseLong(exps[i].split("/")[1]);
//			zero = zero.add(new Fraction(a, b));
//		}
//		return zero.toString();
//	}

	public String fractionAddition(String expression) {
		String[] exps = expression.replace("-", "+-").split("\\+");
		Fraction zero = new Fraction(0, 1);
		for (int i = 0; i < exps.length; i++){
			if (exps[i].isEmpty()) continue;
			long a = Long.parseLong(exps[i].split("/")[0]);
			long b = Long.parseLong(exps[i].split("/")[1]);
			zero = zero.add(new Fraction(a, b));
		}
		return zero.toString();
	}
	
//	public String fractionAddition(String expression) {
//		if (noOpeartor(expression)) {
//			return expression;
//		}
//
//		String ans = "";
//		for (int i = expression.length() - 1; i >= 0; i--) {
//			if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
//				String left = fractionAddition(expression.substring(0, i));
//				String right = fractionAddition(expression.substring(i + 1));
//				int fz1 = Integer.valueOf(left.split("/")[0]);
//				int fz2 = Integer.valueOf(right.split("/")[0]);
//				int fm1 = Integer.valueOf(left.split("/")[1]);
//				int fm2 = Integer.valueOf(right.split("/")[1]);
//
//				int time = fm1 * fm2;
//				switch (expression.charAt(i)) {
//				case '+': {
//					int fz3 = fz1 * fm2 + fz2 * fm1;
//					int fm3 = time;
//					ans = reduce(fz3, fm3);
//				}
//					break;
//				case '-': {
//					int fz3 = fz1 * fm2 - fz2 * fm1;
//					int fm3 = time;
//					ans = reduce(fz3, fm3);
//				}
//					break;
//				default:
//					break;
//				}
//
//				return ans;
//			}
//		}
//		return ans;
//	}
//
//	private String reduce(int fz, int fm) {
//		if (fz == 0) {
//			return 0 + "/" + String.valueOf(fm);
//		}
//
//		int flag = fz > 0 ? 1 : -1;
//		fz = Math.abs(fz);
//		int num = fz;
//
//		boolean canReduce = false;
//		String ans = String.valueOf(fz * flag) + "/" + String.valueOf(fm);
//		for (int i = 2; i <= num; i++) {
//			if (fz % i == 0 && fm % i == 0) {
//				fz /= i;
//				fm /= i;
//				canReduce = true;
//				break;
//			}
//		}
//		if (!canReduce)
//			return ans;
//		return reduce(fz * flag, fm);
//	}
//
//	private boolean noOpeartor(String expression) {
//		for (int i = 0; i < expression.length(); i++) {
//			if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
//				if (i == 0)
//					continue;
//				return false;
//			}
//		}
//		return true;
//	}

	public static void main(String[] args) {
		SolutionDay21_L0592 day = new SolutionDay21_L0592();
		System.out.println(day.fractionAddition("1/3-1/2"));
	}

}
