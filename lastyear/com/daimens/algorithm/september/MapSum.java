package com.daimens.algorithm.september;

import java.util.HashMap;
import java.util.Map;

class MapSum {
	
	class TrieNode{
		TrieNode[] children = new TrieNode[26];
		int val;
	}
	
	TrieNode build(TrieNode root, String key, int val) {
		char[] cs = key.toCharArray();
		if (root == null) root = new TrieNode();
		TrieNode cur = root;
		cur.val = val;
		for (char c : cs) {
			if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
			cur = cur.children[c - 'a'];
			cur.val += val;
		}
		return root;
	}
	
	int search(TrieNode root, String prefix) {
		TrieNode cur = root;
		for (char c : prefix.toCharArray()) {
			if (cur.children[c - 'a'] != null) cur = cur.children[c - 'a'];
			else return 0;
		}
		return cur.val;
	}
	
	Map<String, Integer> mem;
	TrieNode root;
	public MapSum() {
		mem = new HashMap<>();
		root = null;
	}

	public void insert(String key, int val) {
		if (!mem.containsKey(key)) {
			root = build(root, key, val);
			mem.put(key, val);
		}
		else {
			int sub = mem.get(key);
			root = build(root, key, val - sub);
			mem.put(key, val);
		}
	}

	public int sum(String prefix) {
		return search(root, prefix);
	}
}

//Map<String, Integer> mem;
//
//public MapSum() {
//	mem = new HashMap<>();
//}
//
//public void insert(String key, int val) {
//	mem.put(key, val);
//}
//
//public int sum(String prefix) {
//	int sum = 0;
//	for (String pre : mem.keySet()) {
//		for (int i = 0; i <= pre.length(); ++i) {
//			if (prefix.equals(pre.substring(0, i))) {
//				sum += mem.get(pre);
//			}
//		}
//	}
//	return sum;
//}

/// ** Initialize your data structure here. */
// Map<String, Integer> mem;
// Map<String, Integer> set;
// public MapSum() {
// mem = new HashMap<>();
// set = new HashMap<>();
// }
//
// public void insert(String key, int val) {
// if (!set.containsKey(key)){
// set.put(key, val);
// for (int i = 0; i <= key.length(); ++i){
// String sub = key.substring(0, i);
// mem.put(sub,mem.getOrDefault(sub, 0) + val);
// }
// }else{
// int cnt = set.get(key);
// for (int i = 0; i <= key.length(); ++i){
// String sub = key.substring(0, i);
// mem.put(sub, mem.get(sub) - cnt + val);
// }
// }
// }
//
// public int sum(String prefix) {
// if (!mem.containsKey(prefix)) return 0;
// return mem.get(prefix);
// }