package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         43. Multiply Strings
 * 
 *         Given two non-negative integers num1 and num2 represented as strings,
 *         return the product of num1 and num2.
 * 
 *         Note:
 * 
 *         The length of both num1 and num2 is < 110. Both num1 and num2
 *         contains only digits 0-9. Both num1 and num2 does not contain any
 *         leading zero. You must not use any built-in BigInteger library or
 *         convert the inputs to integer directly.
 *
 */
public class SolutionDay08_L0043 {

	public String multiply(String num1, String num2) {
		int n = num1.length();
		int m = num2.length();
		int[] pos = new int[m + n];
		
		char[] n1 = num1.toCharArray();
		char[] n2 = num2.toCharArray();
		
		for (int i = n - 1; i >= 0; --i){
			for (int j = m - 1; j >= 0; --j){
				int mult = (n1[i] - '0') * (n2[j] - '0');
				int p1 = i + j, p2 = i + j + 1;
				int sum = mult + pos[p2];
				
				pos[p1] += sum / 10;
				pos[p2] = sum % 10;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int i = 0;	
		while (i < pos.length && pos[i] == 0) i++;
		for (int j = i; j < pos.length; j++) sb.append(pos[j]);
		return sb.length() == 0 ? "0" : sb.toString();
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0043 day = new SolutionDay08_L0043();
		System.out.println(day.multiply("123", "45"));
	}
}
