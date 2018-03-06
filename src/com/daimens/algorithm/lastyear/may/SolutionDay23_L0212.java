package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         212.Word Search II
 * 
 *         Given a 2D board and a list of words from the dictionary, find all
 *         words in the board.
 * 
 *         Each word must be constructed from letters of sequentially adjacent
 *         cell, where "adjacent" cells are those horizontally or vertically
 *         neighboring. The same letter cell may not be used more than once in a
 *         word.
 * 
 *         For example, Given words = ["oath","pea","eat","rain"] and board =
 * 
 *         [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'],
 *         ['i','f','l','v'] ] Return ["eat","oath"].
 *
 * 
 */
public class SolutionDay23_L0212 {
	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> ans = new ArrayList<>();
		TrieNode root = buildTrie(words);
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				dfs(board, i, j, root, ans);
			}
		}
		return ans;
	}
	
	private void dfs(char[][] board, int i, int j, TrieNode root, List<String> ans){
		char c = board[i][j];
		if (c == '#' || root.next[c-'a'] == null) return;
		root = root.next[c-'a'];
		if (root.word != null){
			ans.add(root.word);
			root.word = null;
		}
		
		board[i][j] = '#';
		if (i > 0) dfs(board, i - 1, j ,root, ans); 
	    if (j > 0) dfs(board, i, j - 1, root, ans);
	    if (i < board.length - 1) dfs(board, i + 1, j, root, ans); 
	    if (j < board[0].length - 1) dfs(board, i, j + 1, root, ans); 
	    board[i][j] = c;
	}
	
	private class TrieNode{
		TrieNode[] next = new TrieNode[26];
		String word;
	}
	
	private TrieNode buildTrie(String[] words){
		TrieNode root = new TrieNode();
		for (String w : words){
			TrieNode p = root;
			for (char c : w.toCharArray()){
				int i = c- 'a';
				if (p.next[i] == null) p.next[i] = new TrieNode();
				p = p.next[i];
			}
			p.word = w;
		}
		return root;
	}
}
