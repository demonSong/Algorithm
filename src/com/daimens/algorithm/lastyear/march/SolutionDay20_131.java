package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 131.Palindrome Partitioning
 * Given a string s,partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example,given s = "aab"
 * Return
 * [
 *  ["aa","b"],
 *  ["a","a","b"]
 * ]
 * 
 *
 */
public class SolutionDay20_131 {
	
	public List<List<String>> partition(String s) {
        
		List<List<String>> ans = new ArrayList<>();
		backtrack(ans, new ArrayList<>(), s, 0);
		return ans;
    }
	
	private void backtrack(List<List<String>> ans, List<String> cur,String s, int l){
		if(cur.size() > 0 && l >= s.length()){
			ans.add(new ArrayList<>(cur));
		}
		for(int i = l; i < s.length(); i++){ //切分是比较神奇的
			if(isPalindrome(s, l, i)){
				if(l==i){
					cur.add(Character.toString(s.charAt(i)));
				}else{
					cur.add(s.substring(l, i+1));
				}
				
				backtrack(ans, cur, s, i+1);  //difference
				cur.remove(cur.size()-1);
			}
		}
	}
	
	private boolean isPalindrome(String str,int l, int r){
		if(l==r) return true;
		while(l<r){
			if(str.charAt(l) != str.charAt(r)) return false;
			l++;
			r--;
		}
		return true;
	}
}
