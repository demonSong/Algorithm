package com.daimens.algorithm.july;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionDay23_L0503 {
	
//	class TrieNode{
//		TrieNode[] children;
//		String str;
//		public TrieNode(){
//			children = new TrieNode[26];
//		}
//	}
//	
//	public TrieNode add(TrieNode root, String str){
//		if (root == null) root = new TrieNode();
//		TrieNode cur = root;
//		for (char c : str.toCharArray()){
//			int pos = c - 'a';
//			if (cur.children[pos] == null) cur.children[pos] = new TrieNode();
//			cur = cur.children[pos];
//		}
//		cur.str = str;
//		return root;
//	}
//	
//	public String search(TrieNode root, String prefix){
//		if (root == null) return null;
//		TrieNode cur = root;
//		for (char c : prefix.toCharArray()){
//			int pos = c -'a';
//			if (cur.children[pos] == null){
//				break;
//			}
//			if (cur.children[pos] != null){
//				cur = cur.children[pos];
//				if (cur.str != null){
//					return cur.str;
//				}
//			}
//		}
//		return null;
//	}
//	
//	TrieNode root;
//	public String replaceWords(List<String> dict, String sentence) {
//		root = new TrieNode();
//		for (String word : dict){
//			root = add(root, word);
//		}
//		String[] replace = sentence.split(" ");
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < replace.length; ++i){
//			String key = search(root, replace[i]);
//			if (key == null){
//				sb.append(replace[i] + " ");
//			}
//			else{
//				sb.append(key + " ");
//			}
//		}
//        return sb.toString().substring(0, sb.length() - 1);
//    }
	
	public String replaceWords(List<String> dict, String sentence) {
		if (dict == null || dict.size() == 0) return sentence;
		
		Set<String> dictSet = new HashSet<>();
		for (String key : dict){
			dictSet.add(key);
		}
		
		StringBuilder sb = new StringBuilder();
		String[] replace = sentence.split(" ");
		for (int i = 0; i < replace.length; ++i){
			String prefix = "";
			for (int j = 0; j < replace[i].length(); ++j){
				prefix = replace[i].substring(0, j + 1);
				if (dictSet.contains(prefix)){
					break;
				}
			}
			sb.append(" " + prefix);
		}
		return sb.deleteCharAt(0).toString();
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0503 day = new SolutionDay23_L0503();
	}
}
