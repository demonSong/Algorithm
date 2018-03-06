package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 423.Reconstruct Original Digits from English
 * Given a non-empty string containing an out-of-order English representation of digits 0-9,
 * output the digits in ascending order.
 * Note:
 * 1. Input contains only lowercase English letters.
 * 2. Input is guaranteed to be valid and can be transformed to its original digits.That means
 * invalid inputs such as "abc" or "zerone" are not permitted.
 * 3. Input length is less than 50,000
 * 
 * Example 1:
 * Input: "owoztneoer"
 * Output: "012"
 * Example 2:
 * Input: "fviefuro"
 * Output: "45"
 *
 */
public class SolutionDay09_423 {
	
//	public String originalDigits(String s){
//		//找0元素,统计每个字符出现的频次么？貌似可以的
//		int[] bucket = new int[26];
//		for (char temp : s.toCharArray()){
//			bucket[temp-'a']++;
//		}
//		
//		int[] digits = new int[10];
//		
//		//定义查找顺序 0 6 8 7 5 9 4 3 2 1
//		char[] series = {'z','x','g','s','v','i','u','r','w','e'};
//		String[] keys = {"zero","six","eight","seven","five","nine","four","three","two","one"};
//		int[] nums = {0,6,8,7,5,9,4,3,2,1};
//		
//		for (int i = 0; i < digits.length; i++){
//			char selectId = series[i];
//			if(bucket[selectId - 'a'] != 0){
//				int frequency = bucket[selectId - 'a'];
//				for (char tmp : keys[i].toCharArray()){
//					bucket[tmp - 'a'] -= frequency;
//				}
//				digits[nums[i]] += frequency;
//			}
//		}
//		
//		
//		StringBuilder res = new StringBuilder();
//		for (int i = 0; i < digits.length; i++){
//			int frequency = digits[i];
//			for (int j = 0; j < frequency; j ++){
//				res.append(i);
//			}
//		}
//		return res.toString();
//	}
	
	public String originalDigits(String s) {
	    int[] count = new int[10];
	    for (int i = 0; i < s.length(); i++){
	        char c = s.charAt(i);
	        if (c == 'z') count[0]++;
	        if (c == 'w') count[2]++;
	        if (c == 'x') count[6]++;
	        if (c == 's') count[7]++; //7-6
	        if (c == 'g') count[8]++;
	        if (c == 'u') count[4]++; 
	        if (c == 'f') count[5]++; //5-4
	        if (c == 'h') count[3]++; //3-8
	        if (c == 'i') count[9]++; //9-8-5-6
	        if (c == 'o') count[1]++; //1-0-2-4
	    }
	    count[7] -= count[6];
	    count[5] -= count[4];
	    count[3] -= count[8];
	    count[9] = count[9] - count[8] - count[5] - count[6];
	    count[1] = count[1] - count[0] - count[2] - count[4];
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i <= 9; i++){
	        for (int j = 0; j < count[i]; j++){
	            sb.append(i);
	        }
	    }
	    return sb.toString();
	}
	
}
