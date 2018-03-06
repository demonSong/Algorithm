package com.daimens.algorithm.june;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         76.Minimum Window Substring
 * 
 *         Given a string S and a string T, find the minimum window in S which
 *         will contain all the characters in T in complexity O(n).
 * 
 *         For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 * 
 *         Note: If there is no such window in S that covers all characters in
 *         T, return the empty string "".
 * 
 *         If there are multiple such windows, you are guaranteed that there
 *         will always be only one unique minimum window in S.
 *
 */
public class SolutionDay09_L0076 {
	
//	public String minWindow(String s, String t) {
//		Set<Character> set = new HashSet<>();
//		for (char c : t.toCharArray()) set.add(c);
//		int n = s.length();
//		char[] cs = s.toCharArray();
//		int[] queue = new int[s.length()];
//		int fir = 0, lst = 0;
//		int min = Integer.MAX_VALUE,lo = 0, hi = 0;
//		for (int i = 0; i < n; ++i){
//			if (set.contains(cs[i])){
//				queue[lst++] = i;
//				set.remove(cs[i]);
//				if (set.isEmpty()){
//					//total
//					int len = queue[lst-1] - queue[fir] + 1;
//					min = len;
//					lo = queue[fir];
//					hi = queue[lst -1];
//				}
//			}
//			
//			if (set.isEmpty()){
//				
//				if (cs[i] == cs[queue[fir]]){
//					fir++;
//					queue[lst++] = i;
//					int len = queue[lst-1] - queue[fir] + 1;
//					if (len < min){
//						min = len;
//						lo = queue[fir];
//						hi = queue[lst -1];
//					}
//				}
//			}
//		}
//		return s.substring(lo, hi+1);
//    }
	
	public String minWindow(String s, String t) {
		int[] map = new int[128];
		int[] pos = new int[128];
		for (int i = 0; i < t.length(); ++i){
			map[t.charAt(i) - 'A']++;
			pos[t.charAt(i) - 'A']++;
		}
		int letterCounter = 0;
		
		int n = s.length();
		char[] cs = s.toCharArray();
		int[] queue = new int[s.length()];
		int fir = 0, lst = 0;
		int min = Integer.MAX_VALUE,lo = -1, hi = -1;
		for (int i = 0; i < n; ++i){
			if (map[cs[i]-'A'] != 0 && letterCounter != t.length()){
				queue[lst++] = i;
				map[cs[i]- 'A']--;
				letterCounter++;
				if (letterCounter == t.length()){
					//total
					int len = queue[lst-1] - queue[fir] + 1;
					min = len;
					lo = queue[fir];
					hi = queue[lst -1];
					continue;
				}
			}
			
			if (letterCounter == t.length()){
				if (pos[cs[i]-'A'] != 0){
					queue[lst++] = i;
					int l = fir;
					int r = lst - 1;
					while (l < r){
						char key = cs[queue[l]];
						for (int k = r; k >= l; --k){
							if (cs[queue[k]] == key){ 
								if (k == l){
									r = l;
									break;
								}
								l++;
								r--;
								break;
							}
						}
					}
					fir = l;
					int len = queue[lst -1] - queue[fir] + 1;
					if (len < min){
						min = len;
						lo = queue[fir];
						hi = queue[lst - 1];
					}
				}
			}
		}
		if (lo == -1 && hi == -1) return "";
		return s.substring(lo, hi+1);
    }
	
	public static void main(String[] args) {
		SolutionDay09_L0076 day = new SolutionDay09_L0076();
		System.out.println(day.minWindow("bba", "ba"));
		System.out.println('z' - 'A');
	}
	
}
