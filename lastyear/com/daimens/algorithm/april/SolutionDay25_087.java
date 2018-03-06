package com.daimens.algorithm.april;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         87.Scramble String
 * 
 *         Given a string s1, we may represent it as a binary tree by
 *         partitioning it to two non-empty substrings recursively.
 * 
 *         Below is one possible representation of s1 = "great":
 * 
 *         great / \ gr eat / \ / \ g r e at / \ a t To scramble the string, we
 *         may choose any non-leaf node and swap its two children.
 * 
 *         For example, if we choose the node "gr" and swap its two children, it
 *         produces a scrambled string "rgeat".
 * 
 *         rgeat / \ rg eat / \ / \ r g e at / \ a t We say that "rgeat" is a
 *         scrambled string of "great".
 * 
 *         Similarly, if we continue to swap the children of nodes "eat" and
 *         "at", it produces a scrambled string "rgtae".
 * 
 *         rgtae / \ rg tae / \ / \ r g ta e / \ t a We say that "rgtae" is a
 *         scrambled string of "great".
 * 
 *         Given two strings s1 and s2 of the same length, determine if s2 is a
 *         scrambled string of s1.
 *
 */
public class SolutionDay25_087 {
	
	//TLE
//	public boolean isScramble(String s1, String s2) {
//		
//		if (s1.length() == 1) return s1.equals(s2);
//		mem = new ArrayList[s1.length()+1];
//		for (int i = 0; i < mem.length; i++){
//			mem[i] = new ArrayList<>();
//		}
//		
//		Set<String> ans =scarmble(s1,s2);
//        return ans.contains(s2);
//    }
//	
//	List<Set<String>>[] mem = null;
//	//Map<Integer, Set<String>> map = new HashMap<>();
//	private Set<String> scarmble(String s1,String s2){
//		
//		//if (map.containsKey(s1.length()) && map.get(s1.length()).contains(s1)) return map.get(s1.length());
//		List<Set<String>> hhs = mem[s1.length()];
//		for(Set<String> hh : hhs){
//			if(hh.contains(s1)) return hh;
//		}
//		
//		Set<String> ans = new HashSet<>();
//		if (s1.length() == 0){
//			ans.add("");
//			return ans;
//		}
//		
//		if(s1.length() == 1){
//			ans.add(s1);
//			//map.put(s1.length(), ans);
//			mem[s1.length()].add(ans);
//			return ans;
//		}
//		
//		for (int i = 1; i < s1.length(); i++){
//			String left = s1.substring(0, i);
//			String right = s1.substring(i,s1.length());
//			
//			//1. left rotate
//			Set<String> leftans = scarmble(left,s2);
//			
//			for (String tmp : leftans){
//				ans.add(tmp+""+right);
//			}
//			
//			//2. right rotate
//			Set<String> rightans = scarmble(right,s2);
//			for (String tmp : rightans){
//				ans.add(left+""+tmp);
//			}
//			
//			//3. left right rotate
//			for(String lefts : leftans){
//				for (String rights : rightans){
//					ans.add(rights + "" + lefts);
//				}
//			}
//			
//			for(String lefts : leftans){
//				for (String rights : rightans){
//					ans.add(lefts + "" + rights);
//				}
//			}
//			
//			//4. no rotate
//			ans.add(left+""+right);
//			
//			if (ans.contains(s2)) return ans;
//		}
//		
//		//map.put(s1.length(), ans);
//		mem[s1.length()].add(ans);
//		
//		return ans;
//	}
	
	public boolean isScramble(String s1, String s2) {
		if (s1.equals(s2))  return true;
		int len = s1.length();
		int[] count = new int[26];
		
		for (int i = 0; i < len; i++){
			count[s1.charAt(i)-'a']++;
			count[s2.charAt(i)-'a']--;
		}
		
		for (int i = 0; i < 26; i++){
			if (count[i] != 0) return false;
		}
		
		for (int i = 1; i <= len-1; i++){
			//pruning
			if(isScramble(s1.substring(0, i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i)))
				return true;
			if( isScramble(s1.substring(0,i), s2.substring(len-i)) && isScramble(s1.substring(i), s2.substring(0,len-i)))
				return true;
		}
		
		return true;
    }
	
	public static void main(String[] args) {
		SolutionDay25_087 day = new SolutionDay25_087();
		day.isScramble("abcdefghij", "efghijcadb");
	}
}
