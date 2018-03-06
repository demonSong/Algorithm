package com.daimens.algorithm.july;

public class SolutionDay23_L0647 {
	
	
//	public int countSubstrings(String s) {
//		char[] cs = s.toCharArray();
//		int n = s.length();
//		int start = 0;
//		int cnt = 0;
//		while (start < n){
//			for (int i = start; i < n; ++i){
//				if (isPalidrome(cs, start, i)){
//					cnt++;
//				}
////				else{
////					break;
////				}
//			}
//			start++;
//		}
//        return cnt;
//    }
//	
//	public boolean isPalidrome(char[] cs, int i, int j){
//		while (i < j){
//			if (cs[i] != cs[j]) return false;
//			i++;
//			j--;
//		}
//		return true;
//	}
	
//	public int countSubstrings(String s) {
//		int n = s.length();
//		char[] cs = s.toCharArray();
//		boolean[][] dp = new boolean[n][n];
//		int ans = 0;
//		for (int i = 0; i < n; ++i){
//			for (int j = i; j >= 0; --j){
//				if (i == j || (dp[i - 1][j + 1] && cs[i] == cs[j]) || (cs[i] == cs[j] && i - j == 1)){
//					dp[i][j] = true;
//					ans ++;
//				}	
//			}
//		}
//		return ans;
//	}
	
	public int countSubstrings(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; ++i){
			ans += extendPalindrom(s.toCharArray(), i, i, 0);
			ans += extendPalindrom(s.toCharArray(), i, i + 1, 0);
		}
		return ans;
	}
	
	public int extendPalindrom(char[] cs, int i, int j, int count){
		while (i >= 0 && j < cs.length && cs[i] == cs[j]){
			count ++;
			i --;
			j ++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0647 day = new SolutionDay23_L0647();
		System.out.println(day.countSubstrings("aba"));
	}

}
