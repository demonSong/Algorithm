package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         336. Palindrome Pairs
 * 
 *         Given a list of unique words, find all pairs of distinct indices (i,
 *         j) in the given list, so that the concatenation of the two words,
 *         i.e. words[i] + words[j] is a palindrome.
 * 
 *         Example 1: Given words = ["bat", "tab", "cat"] Return [[0, 1], [1,
 *         0]] The palindromes are ["battab", "tabbat"] Example 2: Given words =
 *         ["abcd", "dcba", "lls", "s", "sssll"] Return [[0, 1], [1, 0], [3, 2],
 *         [2, 4]] The palindromes are ["dcbaabcd", "abcddcba", "slls",
 *         "llssssll"]
 *
 */
public class SolutionDay09_L0336 {
	
	class TrieNode{
		TrieNode[] children = new TrieNode[26];
		String key = "";
		List<Integer> idx;
		
		public TrieNode(){
			idx = new ArrayList<>();
		}
		
		@Override
		public String toString() {
			return "["+key+"]";
		}
	}
	
	private TrieNode build(TrieNode root, String word, int index){
		if (root == null){
			root = new TrieNode();
		}
		TrieNode cur = root;
		for (char c : word.toCharArray()){
			if (cur.children[c - 'a'] == null){
				cur.children[c - 'a'] = new TrieNode();
			}
			cur.idx.add(index);
			cur = cur.children[c-'a'];
		}
		cur.key = word;
		cur.idx.add(index);
		return root;
	}
	
	private List<Integer> search(TrieNode root, String key){
		TrieNode cur = root;
		for (char c : key.toCharArray()){
			if (cur.children[c - 'a'] != null){
				cur = cur.children[c - 'a'];
			}
		}
		return cur.idx.size() == 1 ? root.idx : cur.idx;
	}
	
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> ans = new ArrayList<>();
		int n = words.length;
		if (n == 0) return ans;
		TrieNode root = null;
		for (int i = 0; i < words.length; ++i){
			if (words[i].isEmpty()) continue;
			root = build(root, words[i], i);
		}
		
		for (int i = 0; i < words.length; ++i){
			if (words[i].isEmpty()){
				for (int j = 0; j < words.length; ++j){
					if (i == j) continue;
					if (isPalindrome(words[j])){
						ans.add(Arrays.asList(i,j));
						ans.add(Arrays.asList(j,i));
					}
				}
				continue;
			}
			List<Integer> idxs = search(root, new StringBuilder(words[i]).reverse().toString());
			for (int j : idxs){	
				if (j == i) continue;
				if (isPalindrome(words[j] + words[i])){
					ans.add(Arrays.asList(j,i));
				}
			}
		}
 		return ans;
    }
	
	private boolean isPalindrome(String s){
		char[] cs = s.toCharArray();
		int i = 0, j = cs.length - 1;
		while (i < j){
			if(cs[i] == cs[j]) {
				i++;
				j--;
			}
			else return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolutionDay09_L0336 day = new SolutionDay09_L0336();
		String[] words = {"a","aa","aaa"};
		System.out.println(day.palindromePairs(words));
	}

}
