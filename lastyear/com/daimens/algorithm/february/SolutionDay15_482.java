package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 482.License Key Formatting
 * Now you are given a string S,which represents a software license key which we would like to format.
 * The string S is composed of alphanumerical characters and dashed. The dashes split the alphanumerical
 * characters within the string into groups.(i.e.if there are M dashes,the string is split into M+1 groups).
 * The dashes in the given string are possible misplaced.
 * We want each group of characters to be of length L(expect for possibly the first group,which could be shorter
 * ,but still must contain at least one character.)To satisfy this requirement,we will reinsert dashes.
 * Additionally,all the lower case letters in the string must be converted to upper case.
 * 
 * So,you are given a non-empty string S,representing a license key to format,and an integer K. And you need 
 * to return the license key formatted according to the description above.
 * Example 1:
 * Input: S = "2-4A0rt-4k", k = 4
 * Output: "24A0-R74k"
 * Explanation: The string S has been split into two parts,each part has 4 characters.
 * 
 * Example 2:
 * Input: S = "2-4A0r7-4k", K = 3
 * Output: "24-A0R-74K"
 * Explanation: The string S has been split into three parts, each part has 3 characters except the first part as it could be shorter as said above.
 * 
 * Note:
 * The length of string S will not exceed 12,000, and K is a positive integer.
 * String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 * String S is non-empty.
 *
 */
public class SolutionDay15_482 {
//	public String licenseKeyFormatting(String S, int K) {
//		S = S.toUpperCase();
//		//插入dashes的位置是需要判断的，从后面插入么
//		String[] datas = S.split("-");
//		StringBuilder sb = new StringBuilder();
//		for (int i =0 ; i < datas.length; i++){
//			sb.append(datas[i]);
//		}
//		String res = sb.reverse().toString();
//		int len = res.length();
//		
//		//找出指定位置插入dashes
//		int begin = 0;
//		StringBuilder resBuilder = new StringBuilder(); 
//		while (len > K){
//			String tmp = res.substring(begin, K + begin);
//			resBuilder.append(tmp);
//			resBuilder.append("-");
//			begin = K + begin;
//			len -= K;
//		}
//		String tmp = res.substring(begin,res.length());
//		resBuilder.append(tmp);
//		return resBuilder.reverse().toString();
//    }
	
	public String licenseKeyFormatting(String S, int K) {
		StringBuilder sb = new StringBuilder();
		for (int i = S.length()-1; i >=0; i--){
			if(S.charAt(i) != '-'){
				sb.append(sb.length() % (K + 1) == K ? '-' : "").append(S.charAt(i));
			}
		}
		return sb.reverse().toString().toUpperCase();
	}
	
	public static void main(String[] args) {
		SolutionDay15_482 day = new SolutionDay15_482();
		String S = "2-4A0r7-4k";
		day.licenseKeyFormatting(S, 4);
	}
}
