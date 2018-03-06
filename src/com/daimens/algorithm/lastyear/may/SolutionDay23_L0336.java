package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         336.Palindrome Pairs
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
 *
 */
public class SolutionDay23_L0336 {
	
	private class TrieNode{
		TrieNode[] children = new TrieNode[27];
		String word;
		int index = -1;
 	}
	
	private TrieNode build(String[] words){
		TrieNode root = new TrieNode();
		for (int i = 0; i < words.length; i++){
			TrieNode p = root;
			for (char c : words[i].toCharArray()){
				int j = c-'a';
				if (p.children[j] == null) p.children[j] = new TrieNode();
				p = p.children[j];
			}
			p.word = words[i];
			p.index = i;
		}
		return root;
	}
	
	private int search(char[] word, int pos, TrieNode root){
		if (pos == word.length) return root.index;
		char c= word[pos];
		if (root.children[c-'a'] == null) return -1;
		root = root.children[c-'a'];
		if (root.word != null){
			if (isPalindrome(word, pos+1, word.length-1)) return root.index;
		}
		return search(word, pos+1, root);
	}
	
	private boolean isPalindrome(char[] word, int s, int e){
		while (s < e){
			if(word[s] != word[e]) return false;
			s++;
			e--;
		}
		return true;
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		TrieNode root = build(words);
		List<List<Integer>> ans = new ArrayList<>();
		
		for (int i = 0; i < words.length; i++){
			if (words[i].isEmpty()){
				for (int j = 0; j < words.length; j++){
					if (i == j) continue;
					if (isPalindrome(words[j].toCharArray(), 0, words[j].length()-1)){
						List<Integer> tmp = new ArrayList<>();
						tmp.add(i);
						tmp.add(j);
						ans.add(tmp);
						tmp = new ArrayList<>();
						tmp.add(j);
						tmp.add(i);
						ans.add(tmp);
					}
				}
			}
		}
		
		for (int i = 0; i < words.length; i++){
			StringBuilder sb = new StringBuilder(words[i]);
			List<Integer> tmp = new ArrayList<>();
			int index = search(sb.reverse().toString().toCharArray(), 0, root);
			if (index != -1){
				if (i == index) continue;
				tmp.add(index);
				tmp.add(i);
				ans.add(tmp);
			}
		}
		return ans;
	}

}
