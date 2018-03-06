package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 392.Is Subsequence
 * Given a string s and a string t, check if s is subsequence of t.
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very 
 * long (length ~= 500,000)string,and s is a short string(<=100).
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting some 
 * (can be none) of the characters without disturbing the relative position of the remaining characters.
 * (ie. "ace" is a subsequence of "abcde" while "aec" is not).
 * Example 1:
 * S = "abc", t = "ahbgdc"
 * Return true.
 * 
 * Example 2:
 * S = "axc", t = "ahbgdc"
 * Return false.
 * 
 * Follow up:
 * If there are lots of incoming S, say S1, S2,...,Sk where k >= 1B,and you want to check one by one to see
 * if T has its subsequence. In this scenario,how would you change your code?
 * 
 *
 */
public class SolutionDay06_392 {
	
//	public boolean isSubsequence(String s, String t){
//        if(s.equals("")) return true;
//		char[] tc = t.toCharArray();
//		char[] sc = s.toCharArray();
//		int countT = 0;
//		int countS = 0;
//		while (countS < s.length() - 1) {
//			countS++;
//			while (countT < t.length() - 1) {
//				countT++;
//				if (sc[countS] == tc[countT]) {
//					break;
//				}
//			}
//			if (countT == t.length() - 1) { // 如果已经是最后一个元素了
//				break;
//			}
//		}
//
//		if (countT < t.length() -1 && countS == s.length() - 1 || (countT == t.length() -1 && countS == s.length() -1 && tc[t.length()-1] == sc[s.length()-1])) {
//			return true;
//		}
//		
//		else{
//			return false;
//		}
//	}
	
//	public boolean isSubsequence(String s, String t){
//		if(s.length() == 0) return true;
//		int indexS = 0 ,indexT = 0;
//		while(indexT < t.length()){
//			if(t.charAt(indexT) == s.charAt(indexS)){
//				indexS ++;
//				if(indexS == s.length()) return true;
//			}
//			indexT ++;
//		}
//		
//		return false;
//	}
	
	public boolean isSubsequence(String s, String t){
		
		if (s.isEmpty()) return true;
		int n = s.length();
		boolean[] isSeq = new boolean[n];
		
		int index = 0;
		for (int i = 0; i < t.length(); i++){
			if (s.charAt(index) == t.charAt(i)){
				isSeq[index++] = true;
				if (isSeq[n-1]) return true;
			}
		}
		return isSeq[n-1];
	}

}
