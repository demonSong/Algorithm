package com.daimens.algorithm.july;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AutocompleteSystem {
	
	class TrieNode{
		TrieNode[] children = new TrieNode[32];
		String str;
		int times;
	}
	
	public TrieNode add(TrieNode root, String str, int times){
		char[] cs = str.toCharArray();
		if (root == null) root = new TrieNode();
		TrieNode cur = root;
		for (char c : cs){
			int pos = 0;
			if (c == ' ') pos = 27;
			else pos = c - 'a';
			if (cur.children[pos] == null) cur.children[pos] = new TrieNode();
			cur = cur.children[pos];
		}
		if (cur.str != null) cur.times += times;
		else cur.times = times;
		cur.str = str;
		return root;
	}
	
	public TrieNode search(TrieNode root, String prefix){
		TrieNode cur = root;
		for (char c : prefix.toCharArray()){
			int pos = 0;
			if (c == ' ') pos = 27;
			else pos = c - 'a';
			if (cur.children[pos] != null) cur = cur.children[pos];
			else return null;
		}
		return cur;
	}
	
	TrieNode root;
	String prefix;
	public AutocompleteSystem(String[] sentences, int[] times) {
		for (int i = 0; i < sentences.length; ++i){
			root = add(root, sentences[i], times[i]);
		}
		prefix = "";
    }
	
	public List<TrieNode> bfs(TrieNode cur){
		List<TrieNode> ans = new ArrayList<>();
		if (cur == null) return ans;
		Queue<TrieNode> queue = new LinkedList<>();
		queue.offer(cur);
		while (!queue.isEmpty()){
			TrieNode node = queue.poll();
			if (node.str != null) ans.add(node);
			for (int i = 0; i < 32; ++i){
				if (node.children[i] != null) queue.offer(node.children[i]);
			}
		}
		return ans;
	}
    
    public List<String> input(char c) {
    	if (c == '#'){
    		root = add(root, prefix, 1);
    		prefix = "";
    		return new ArrayList<>();
    	}
    	prefix += c;
    	List<TrieNode> candicates = bfs(search(root, prefix));
    	Collections.sort(candicates, new Comparator<TrieNode>() {
			@Override
			public int compare(TrieNode o1, TrieNode o2) {
				return o2.times != o1.times ? o2.times - o1.times : o1.str.compareTo(o2.str);
			}
		});
    	List<String> ans = new ArrayList<>();
    	for (int i = 0; i < Math.min(candicates.size(), 3); ++i){
    		ans.add(candicates.get(i).str);
    	}
    	return ans;
    }

}
