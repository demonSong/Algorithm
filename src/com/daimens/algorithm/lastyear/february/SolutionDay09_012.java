package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 12.Integer to Roman
 * Given an integer,convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 */
public class SolutionDay09_012 {
	
	public String intToRoman(int num){
		String M[] = {"", "M", "MM", "MMM"}; //1000,2000,3000
	    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};//100,200,...,900
	    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};//10,20,...,90
	    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};//1,2,...,9
	    
		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	}

}
