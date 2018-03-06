package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         97.Interleaving String
 * 
 *         Given s1, s2, s3, find whether s3 is formed by the interleaving of s1
 *         and s2.
 * 
 *         For example, Given: s1 = "aabcc", s2 = "dbbca",
 * 
 *         When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return
 *         false.
 *
 */
public class SolutionDay25_097 {
	
//	public boolean isInterleave(String s1, String s2, String s3) {
//        return true;
//    }
	
//	public boolean isInterleave(String s1, String s2, String s3) {
//		
//		if(s1.length() + s2.length() != s3.length()) return false;
//		
//		boolean[] f1 = new boolean[s1.length()+1];
//		boolean[] f2 = new boolean[s2.length()+1];
//		
//		f1[0] = true;
//		f2[0] = true;
//		
//		int p1 = 1, p2 = 1;
//		for (int i = 0; i < s3.length(); i++){
//			char x = s3.charAt(i);
//			if ((p1- 1) != s1.length() && f1[p1-1] && x == s1.charAt(p1-1)){
//				f1[p1++] = true;
//			}
//			
//			if ((p2- 1) != s2.length() && f2[p2-1] && x == s2.charAt(p2-1)){
//				f2[p2++] = true;
//			}
//		}
//		
//		return f1[s1.length()] && f2[s2.length()];
//	}
	
//	public boolean isInterleave(String s1, String s2, String s3) {
//		
//		if(s1.length() + s2.length() != s3.length()) return false;
//		
//		if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) return true;
//		char x = s3.charAt(0);
//		
//		if (s1.length() == 0 && s2.length() != 0){
//			if (x != s2.charAt(0)){
//				return false;
//			}else{
//				return isInterleave(s1, s2.substring(1,s2.length()), s3.substring(1,s3.length()));
//			}
//		}
//		
//		if (s2.length() == 0 && s1.length() != 0){
//			if (x != s1.charAt(0)){
//				return false;
//			}else{
//				return isInterleave(s1.substring(1, s1.length()), s2, s3.substring(1,s3.length()));
//			}
//		}
//		
//		if (x == s1.charAt(0) && x != s2.charAt(0)){
//			return isInterleave(s1.substring(1, s1.length()), s2, s3.substring(1,s3.length()));
//		}
//		
//		if (x != s1.charAt(0) && x == s2.charAt(0)){
//			return isInterleave(s1, s2.substring(1, s2.length()), s3.substring(1,s3.length()));
//		}
//		
//		if (x == s1.charAt(0) && x == s2.charAt(0)){
//			return isInterleave(s1.substring(1, s1.length()), s2, s3.substring(1,s3.length())) ||
//					      isInterleave(s1, s2.substring(1, s2.length()), s3.substring(1,s3.length()));
//		}
//		
//		return false;
//	}
	
	// 遍历递增量，更新规则
	public boolean isInterleave(String s1, String s2, String s3) {
		
		int m = s1.length();
		int n = s2.length();
		if (m + n != s3.length()) return false;
		boolean[][] valid = new boolean[m+1][n+1];
		valid[0][0] = true;
		
		for (int j = 1; j < n + 1; j++){
			valid[0][j] = valid[0][j-1] && (s2.charAt(j-1) == s3.charAt(j-1));
		}
		
		for (int i = 1; i < m + 1; i++){
			valid[i][0] = valid[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
		}
		
		for (int i = 1; i < m + 1; i++){
			for (int j = 1; j < n + 1; j++){
				int k = i + j;
				valid[i][j] = s1.charAt(i-1) == s3.charAt(k-1) && valid[i-1][j] ||
						s2.charAt(j-1) == s3.charAt(k-1) && valid[i][j-1];
			}
		}
		
		return valid[m][n];
	}
	
	
//	public boolean isInterleave(String s1, String s2, String s3) {
//		char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
//		int m = s1.length(), n = s2.length();
//		if(m + n != s3.length()) return false;
//		return dfs(c1, c2, c3, 0, 0, 0, new boolean[m + 1][n + 1]);
//	}
//	
//	private boolean dfs(char[] c1, char[] c2, char[] c3, int i , int j, int k, boolean[][] invalid){
//		if (invalid[i][j]) return false;
//		if (k == c3.length) return true;
//		boolean valid = 
//				i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i+1, j, k+1, invalid) ||
//				j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j+1, k+1, invalid);
//		if (!valid) invalid[i][j] = true;
//		return valid;
//	}
	
	public static void main(String[] args) {
		SolutionDay25_097 day = new SolutionDay25_097();
		System.out.println(day.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println();
	}
}
