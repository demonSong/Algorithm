package com.daimens.algorithm.november;

/**
 * 
 * @author Demon Song
 * 459. Repeated SubString Pattern
 * Given a non-empty string check if it can be constructed 
 * by taking a substring of it and appending multiple copies 
 * of the substring together. 
 * You may assume the given string consists of lowercase 
 * English letters only and its length will not exceed 10000.
 * Example:
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 * Input: "aba"
 * Output: False
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 *
 */
public class SolutionDay29 {
	public boolean repeatedSubstringPattern(String str){
		//暴力 第一个和第二个，并且找寻 第三个以后的子串  否 直接第二个和第三个
		
		//subString of it and appending multiple copies of the substring together
		//so "ababc" is false
		//还是用hashMap记录？ 不需要
		
		//由推论 构造结论 与之比较
		if (str == null || str.length() ==0) return false;
		
		for (int j = 1; j < str.length()/2+1; j++){
			String subStr = str.substring(0, j);
			if(str.length() % j ==0){ //能够被整除 那还是有希望的
				StringBuilder sb = new StringBuilder();
				for (int i = 0 ; i < str.length() /j; i++){
					sb.append(subStr);
				}
				if(sb.toString().equals(str)){
					return true;
				}
			}
//			for (int i = j; i < str.length()-j; i +=(j+1) ){
//				String tmp = str.substring(i,i+j);
//				if(!tmp.equals(subStr)){
//					break; //不等不能说是false
//				}
//			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay29 day29 = new SolutionDay29();
		day29.repeatedSubstringPattern("abab");
	}

}
