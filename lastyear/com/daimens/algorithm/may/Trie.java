package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         208. Implement Trie (Prefix Tree)
 * 
 *         Implement a trie with insert, search, and startsWith methods.
 * 
 *         Note: You may assume that all inputs are consist of lowercase letters
 *         a-z.
 *
 */
public class Trie {
	
	private class TrieNode{
		TrieNode[] childern = new TrieNode[26];
		String word;
	}
	
	TrieNode root;
	public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()){
        	int i = c - 'a';
        	if (p.childern[i] == null) p.childern[i] = new TrieNode();
        	p = p.childern[i];
        }
        p.word = word;
    }
    
    public boolean search(String word) {
    	return match(word.toCharArray(), 0, root);
    }
    
    public boolean startsWith(String prefix) {
        return matchPrefix(prefix.toCharArray(), 0, root);
    }
    
    private boolean matchPrefix(char[] chs, int pos, TrieNode root){
    	if (chs.length == pos) return true;
    	return root.childern[chs[pos]-'a'] != null && matchPrefix(chs, pos+1, root.childern[chs[pos]-'a']);
    }
    
    private boolean match(char[] chs, int pos, TrieNode root){
    	if (chs.length == pos) return root.word != null;
    	return root.childern[chs[pos]-'a'] != null && match(chs, pos+1, root.childern[chs[pos]-'a']);
    }
    
    
    public static void main(String[] args) {
    	Trie trie = new Trie();
    	trie.insert("a");
    	trie.search("a");
    	trie.startsWith("a");
	}

}
