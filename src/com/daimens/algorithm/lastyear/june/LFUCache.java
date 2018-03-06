package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         460. LFU Cache
 * 
 *         Design and implement a data structure for Least Frequently Used (LFU)
 *         cache. It should support the following operations: get and put.
 * 
 *         get(key) - Get the value (will always be positive) of the key if the
 *         key exists in the cache, otherwise return -1. put(key, value) - Set
 *         or insert the value if the key is not already present. When the cache
 *         reaches its capacity, it should invalidate the least frequently used
 *         item before inserting a new item. For the purpose of this problem,
 *         when there is a tie (i.e., two or more keys that have the same
 *         frequency), the least recently used key would be evicted.
 * 
 *         Follow up: Could you do both operations in O(1) time complexity?
 *         
 **/
public class LFUCache {
	
	class Node{
		public int count;
		public LinkedHashSet<Integer> keys;
		public Node next;
		public Node prev;
		
		public Node(int count){
			this.count = count;
			keys = new LinkedHashSet<>();
			prev = next = null;
		}
	}
	
	private Node head = null;
	private int cap = 0;
	
	Map<Integer, Integer> cache;
	Map<Integer, Node> nodeHash;
    public LFUCache(int capacity) {
        cache = new HashMap<>();
        nodeHash = new HashMap<>();
        this.cap = capacity;
    }
    
    private void increaseCount(int key){
    	Node node = nodeHash.get(key);
    	node.keys.remove(key);
    	
    	if (node.next == null){
    		node.next = new Node(node.count + 1);
    		node.next.prev = node;
    		node.next.keys.add(key);
    	}
    	else if (node.next.count == node.count + 1){
    		node.next.keys.add(key);
    	}
    	else{
    		Node tmp = new Node(node.count + 1);
    		tmp.keys.add(key);
    		tmp.prev = node;
    		tmp.next = node.next;
    		node.next.prev = tmp;
    		node.next = tmp;
    	}
    	
    	nodeHash.put(key, node.next);
    	if (node.keys.isEmpty()) remove(node);
    }
    
    private void addToHead(int key) {
    	if (head == null){
    		head = new Node(0);
    		head.keys.add(key);
    	}else if (head.count > 0){
    		Node node = new Node(0);
    		node.keys.add(key);
    		node.next = head;
    		head.prev = node;
    		head = node;
    	}else{
    		head.keys.add(key);
    	}
    	nodeHash.put(key, head);
    }
    
    private void removeOld(){
    	if (head == null) return;
    	int old = head.keys.iterator().next();
    	head.keys.remove(old);
    	if (head.keys.size() == 0) remove(head);
    	nodeHash.remove(old);
    	cache.remove(old);
    }
    
    private void remove(Node node) {
    	if (node.prev == null){
    		head = node.next;
    	}else{
    		node.prev.next = node.next;
    	}
    	if (node.next != null){
    		node.next.prev = node.prev;
    	}
    }
    
    public int get(int key) { //update
    	if (!cache.containsKey(key)) return -1;
    	increaseCount(key);
    	return cache.get(key);
    }
    
    public void put(int key, int value) { //remove
    	if (cap == 0) return;
    	if (cache.containsKey(key)){
    		cache.put(key, value);
    	}else{
    		if (cache.size() < cap){
    			cache.put(key, value);
    		}else{
    			removeOld();
    			cache.put(key, value);
    		}
    		addToHead(key);
    	}
    	increaseCount(key);
    }
}
