package com.daimens.algorithm.november;

/**
 * 
 * @author Demon Song
 * 415. Add Strings
 * Given two non-negative numbers num1 and num2 represented
 *         as string,return the sum of num1 and num2
 * 
 *         Note: 1.The length of both num1 and num2 is < 5100 2.Both num1 and
 *         num2 contains only digits 0-9 3.Both num1 and num2 dose not contain
 *         any leading zero. 4.You must not use any built-in BigInteger library
 *         or convert the inputs to integer directly.
 *
 */
public class SolutionDay27 {
	
	public String addStrings(String num1, String num2) {
		if (num1.length() >= 5100 || num2.length() >= 5100){
            throw new IllegalArgumentException("no results");
        }
		String longer = num1;
		String shorter = num2;
		if(longer.length() < shorter.length()){
			longer = num2;
			shorter = num1;
		}
		int n = longer.length();
		int m = shorter.length();
		char[] l = longer.toCharArray();
		char[] s = shorter.toCharArray();
		
		//余数
		int remainder = 0;
		char base = '0';
		
		int tmp;
		StringBuilder res = new StringBuilder();
		//从末尾开始加
		while( (m--) > 0){ // m > 0 ? , m = m-1;
			n--;
			tmp = l[n]+s[m] - 2*base +remainder;
			remainder = tmp / 10;
			res.append(tmp % 10);
		}
		
		while ((n--) > 0){
			tmp = l[n] - base +remainder;
			remainder = tmp / 10;
			res.append(tmp % 10);
		}
		
		//处理最后的进位
		if (remainder != 0) res.append(remainder);
		return res.reverse().toString();
	}
	
	public static void main(String[] args) {
		//System.out.println(Integer.valueOf("6913259244"));
		for (char c : "123456789".toCharArray()){
			System.out.println(c);
		}
	}
}
