package com.daimens.algorithm.january;

import java.util.HashMap;

/**
 * 
 * @author Demon Song
 * 168.Excel Sheet Column Title
 * Given a positive integer,return its corresponding column title as appear in an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 *
 */
public class SolutionDay25_168 {
	/*public String convertToTitle(int n){
		int k  = logK(n);
		StringBuilder ret = new StringBuilder();
		int x = n-1;
		while((k--) !=0){
			int tmp = x / (int) Math.pow(26, k);
			char s = (char)(tmp-1+'A');
			x = (int) (x - (int)tmp * Math.pow(26, k));
			ret.append(s);
		}
		return ret.reverse().toString();
	}
	
	//测试 生成的n是否符合 求解公式得出的解
	public int[] testLogK(){
		
		int[] array = new int[26*26*26-26*26+1];
		for (int i = 26*26+1; i <= 26*26*26; i++){
			array[i-26*26-1] = logK(i);
		}
		return array;
	}
	
	private int logK(int n){
		int k = (int) Math.floor(Math.log(n)/Math.log(26))+1;
		if(n == (int)Math.pow(26, k-1)){
			return k-1;
		}
		return k;
	}*/
	
	public String convertToTitle(int n){
		String result="";
		while( n > 0){
			result = (char)((n-1)%26 +'A') + result;
			n = (n-1)/26;
		}
		return result;
	}
	
	public static void main(String[] args) {
		SolutionDay25_168 day25_168 = new SolutionDay25_168();
	}
}
