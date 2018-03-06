package com.daimens.algorithm.may;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         316.Remove Duplicate Letters
 * 
 *         Given a string which contains only lowercase letters, remove
 *         duplicate letters so that every letter appear once and only once. You
 *         must make sure your result is the smallest in lexicographical order
 *         among all possible results.
 * 
 *         Example: Given "bcabc" Return "abc"
 * 
 *         Given "cbacdcbc" Return "acdb"
 *
 */
public class SolutionDay20_L0316 {
	
//	public String removeDuplicateLetters(String s) {
//        char[] c = s.toCharArray();
//        int[] map = new int[26];
//        for (int i = 0; i < c.length; i++){
//        	map[c[i]-'a']++;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < map.length; i++){
//        	if (map[i] >= 1){
//        		char cc = (char) (i + 'a');
//        		sb.append(new String(new char[]{cc}));
//        	}
//        }
//        return sb.toString();
//    }
	
//	public String removeDuplicateLetters(String s) {
//		char[] c = s.toCharArray();
//		int[]  map = new int[26];
//		for (int i = 0; i < c.length; i++){
//			map[c[i]-'a']++;
//		}
//		
//		for (int i = 0; i < c.length; i++){
//			if(map[c[i]-'a'] >= 2){
//				map[c[i]-'a']--;
//				c[i] = '#';
//			}
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < c.length; i++){
//			if (c[i] != '#'){
//				sb.append(new String(new char[]{c[i]}));
//			}
//		}
//		return sb.toString();
//	}
	
//	public String removeDuplicateLetters(String s) {
//		char[] c = s.toCharArray();
//		int[]  map = new int[26];
//		for (int i = 0; i < c.length; i++){
//			map[c[i]-'a']++;
//		}
//		
//		int lf = 0, rt = s.length()-1;
//		while (lf < rt){
//			while (lf < rt && ! (map[c[lf]-'a'] >= 2)) lf ++;
//			while (lf < rt && ! (map[c[rt]-'a'] >= 2)) rt --;
//			
//			if (lf >= rt) break;
//			
//			if (c[lf] <= c[rt]){
//				map[c[lf]-'a']--;
//				c[lf] = '#';
//				lf++;
//			}else{
//				map[c[rt]-'a']--;
//				c[rt] = '#';
//				rt--;
//			}
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < c.length; i++){
//			if (c[i] != '#'){
//				sb.append(new String(new char[]{c[i]}));
//			}
//		}
//		return sb.toString();
//	}
	
//	public String removeDuplicateLetters(String s) {
//        int[] cnt = new int[26];
//        char[] c = s.toCharArray();
//        int n = c.length;
//        for (int i = 0; i < n; i++){
//        	cnt[c[i]-'a']++;
//        }
//        //字母最小的 index, 删除最小index左边的元素该index右边一定存在
//        int pos = 0;
//        for (int i = 0; i < n; i++){
//        	if (c[i] < c[pos]) pos = i;
//        	if(--cnt[c[i]-'a'] == 0) break;
//        }
//        
//        return n == 0 ? "" : c[pos] + removeDuplicateLetters(s.substring(pos+1).replaceAll("" + s.charAt(pos), ""));
//    }
	
	public String removeDuplicateLetters(String s) {
		int len = s.length();
		
		if (s.isEmpty()) return "";
		if (len == 1) return s;
		
		char[] cs = s.toCharArray();
		int[] cnt = new int[26];
		for (int i = 0; i < len; i++){
			cnt[cs[i]-'a']++;
		}
		
		int index = -1;
		for (int i = 0; i < len; i++){
			if(cnt[cs[i]-'a'] == 1){
				index = i;
				break;
			}
		}
		
		if (index == -1){
			for (int i = 0; i < 26; i++){
				if(cnt[i] >= 1){
					//s = s.replaceAll(new String(cc), new String(new char[]{(char) (i+'a')}));
					char target = (char) (i + 'a');
					int k = -1;
					for (int j = 0; j < s.length(); j++){
						if (cs[j] == target){
							k = j;
							break;
						}
					}
					
					String left = s.substring(0, k);
					String right = s.substring(k+1).replace(""+target, "");
					return removeDuplicateLetters(left+target+right);
				}
			}
		}
		
		StringBuilder left = new StringBuilder();
		for (int i = 0; i < index; i++){
			if (cs[i] < cs[index]){
				left.append(""+cs[i]);
			}
		}
		
		String right = s.substring(index+1);
		for(int i = 0; i < left.toString().length(); i++){
			right = right.replaceAll(""+left.toString().charAt(i), "");
		}
		return removeDuplicateLetters(left.toString())+cs[index]+removeDuplicateLetters(right);
	}
	
	
	public static void main(String[] args) {
		SolutionDay20_L0316 day = new SolutionDay20_L0316();
		day.removeDuplicateLetters("ccacbaba");
		//day.removeDuplicateLetters("aabceecbzz");
		System.out.println(""+'s'+'d');
		String ans = "aaccvvv";
		ans = ans.replaceAll("a", "");
		System.out.println(ans);
	}
}
