package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         211. Add and Search Word - Data structure design
 * 
 *         Design a data structure that supports the following two operations:
 * 
 *         void addWord(word) bool search(word) search(word) can search a
 *         literal word or a regular expression string containing only letters
 *         a-z or .. A . means it can represent any one letter.
 * 
 *         For example:
 * 
 *         addWord("bad") addWord("dad") addWord("mad") search("pad") -> false
 *         search("bad") -> true search(".ad") -> true search("b..") -> true
 *         Note: You may assume that all words are consist of lowercase letters
 *         a-z.
 *
 */

public class WordDictionary {

	private class TrieNode {
		TrieNode[] next = new TrieNode[26];
		String word;
	}

	TrieNode root;

	public WordDictionary() {
		root = new TrieNode();
	}

	public void addWord(String word) {
		TrieNode p = root;
		for (char c : word.toCharArray()) {
			int i = c - 'a';
			if (p.next[i] == null)
				p.next[i] = new TrieNode();
			p = p.next[i];
		}
		p.word = word;
	}

	public boolean search(String word) {
		return match(word.toCharArray(), 0, root);
	}
	
	private boolean match(char[] chs, int pos, TrieNode node){
		if (pos == chs.length) return node.word != null;
		if (chs[pos] != '.'){
			return node.next[chs[pos]-'a'] != null && match(chs, pos+1, node.next[chs[pos]-'a']);
		}else{
			for (char c = 'a'; c <= 'z'; c++){
				if (node.next[c-'a'] != null){
					if(match(chs, pos+1, node.next[c-'a'])){
						return true;
					}
				}
			}
		}
		return false;
	}

//	private boolean dfs(char[] words, int pos, TrieNode root) {
//		if (pos >= words.length) return false;
//		char c = words[pos];
//		if (c != '.' && root.next[c - 'a'] == null)
//			return false;
//		if (c != '.') {
//			root = root.next[c - 'a'];
//			if (root.word != null && root.word.length() == words.length) {
//				return true;
//			}
//			if (dfs(words, pos + 1, root))
//				return true;
//			return false;
//		} else {
//			for (char cc = 'a'; cc <= 'z'; cc++) {
//				if (root.next[cc - 'a'] != null) {
//					root = root.next[cc - 'a'];
//					if (root.word != null && root.word.length() == words.length) {
//						return true;
//					}
//					if (dfs(words, pos + 1, root))
//						return true;
//					return false;
//				}
//			}
//		}
//		return false;
//	}
	
	public static void main(String[] args) {
		WordDictionary wd = new WordDictionary();
		wd.addWord("bad");
		wd.addWord("dad");
		wd.addWord("mad");
		wd.search(".at");
		wd.addWord("bat");
		wd.search(".at");
		wd.search("pad");
		wd.search("bad");
		wd.search(".ad");
		wd.search("b..");
	}
}


