package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 467.Unique Substrings in Wraparound String
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", 
 * so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. 
 * In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 * Example 1:
 * Input: "a"
 * Output: 1
 * Explanation: Only the substring "a" of string "a" is in the string s.
 * Example 2:
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 * Example 3:
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 *
 */
public class SolutionDay21_467 {
	
	//time limit exceeded
	
//	public int findSubstringInWraproundString(String p) {
//		Set<String> set = new HashSet<>();
//		for (int i = 0; i < p.length(); i++) { //遍历可以做些变换
//			dfs(p, i, i,p.length()-1,set);
//		}
//		return set.size();
//    }
//	
//	
//	
//	private void dfs(String p,int start,int pos,int end,Set<String> set){
//		if(end < pos){
//			return;
//		}
//		
//		String s = p.substring(start,pos+1);
//		if(isWraproundString(s)){
//			
//			set.add(s);
//		}
//		//dfs 的操作太费时了
//		dfs(p, start, pos + 1, end, set);
//		
//	}
//	
//	private boolean isWraproundString(String s){
//		
//		if(s.length() == 0) return false;
//		
//		
//		for(int i = 1; i < s.length(); i++){
//			if(s.charAt(i)-s.charAt(i-1) == -25){
//				continue;
//			}
//			if(s.charAt(i)-s.charAt(i-1) != 1 ){
//				return false;
//			}
//		}
//		return true;
//	}
	
	//stackoverflow 在子集中还存在一些重复的元素是需要去除的，所以这种解决方案是错误的
//	public int findSubstringInWraproundString(String p) {
//		Set<String> set = new HashSet<>();
//		for (int i = 0; i < p.length();) { // 遍历可以做些变换
//
//			count = 0;
//			dfs(p + "8", i, i, p.length(), set, 0);
//			i += count;
//		}
//
//		int count = 0;
//		for (String tmp : set) {
//			int n = tmp.length();
//			count += n * (n + 1) / 2;
//		}
//
//		return count;
//	}
//	
//	
//	int count = 0;
//	private void dfs(String p,int start,int pos,int end,Set<String> set,int len){
//		if(end >= pos){
//			String s = p.substring(start,pos+1);
//			if(isWraproundString(s)){
//				len ++;
//				dfs(p, start, pos + 1, end, set,len);
//			}else{
//				set.add(s.substring(0,s.length()-1));
//				count = len;
//			}
//		}
//	}
//	
//	private boolean isWraproundString(String s){
//		
//		if(s.length() == 0) return false;
//		
//		
//		for(int i = 1; i < s.length(); i++){
//			if(s.charAt(i)-s.charAt(i-1) == -25){
//				continue;
//			}
//			if(s.charAt(i)-s.charAt(i-1) != 1 ){
//				return false;
//			}
//		}
//		return true;
//	}
	
	public int findSubstringInWraproundString(String p) {

		int[] count = new int[26];

		int maxLengthCur = 0;

		for (int i = 0; i < p.length(); i++) {
			if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {
				maxLengthCur++;
			} else {
				maxLengthCur = 1;
			}

			int index = p.charAt(i) - 'a';
			count[index] = Math.max(count[index], maxLengthCur);
		}
		
		int sum = 0;
		for (int i = 0; i < 26; i++) {
			sum += count[i];
		}
		
		return sum;
	}
	
	
	public static void main(String[] args) {
		SolutionDay21_467 day = new SolutionDay21_467();
		
		
		String string  = "za";
		System.out.println(string.substring(0, 0));
		day.findSubstringInWraproundString("abcdefvzabc");
	}

}
