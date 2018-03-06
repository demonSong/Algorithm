package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         146. LRU Cache
 * 
 *         Design and implement a data structure for Least Recently Used (LRU)
 *         cache. It should support the following operations: get and put.
 * 
 *         get(key) - Get the value (will always be positive) of the key if the
 *         key exists in the cache, otherwise return -1. put(key, value) - Set
 *         or insert the value if the key is not already present. When the cache
 *         reached its capacity, it should invalidate the least recently used
 *         item before inserting a new item.
 * 
 *         Follow up: Could you do both operations in O(1) time complexity?
 * 
 ***/
public class LRUCache {
	
	class Node{
		public int key;
		public Node next;
		public Node prev;
		
		public Node(int key){
			this.key = key;
			next = null;
			prev = null;
		}
	}
	
	private Node head = null;
	private Node tail = null;
	
	private int cap = 0;
	Map<Integer, Integer> cache;
	Map<Integer, Node> nodeMap;
	
    public LRUCache(int capacity) {
        cache = new HashMap<>();
        nodeMap =new HashMap<>();
        head = new Node(Integer.MAX_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        
        this.cap = capacity;
    }
    
    private void update(int key){
    	Node node;
    	if (nodeMap.containsKey(key)){
    		node = nodeMap.get(key);
        	node.prev.next = node.next;
        	node.next.prev = node.prev;
    	}
    	else{
    		node = new Node(key);
    	}
    	tail.prev.next = node;
    	node.prev = tail.prev;
    	node.next = tail;
    	tail.prev = node;
    	nodeMap.put(key, node);
    }
    
    private void remove(){
    	Node node = head.next;
    	
    	head.next = node.next;
    	node.next.prev = head;
    	
    	cache.remove(node.key);
    	nodeMap.remove(node.key);
    }
    
    public int get(int key) {
    	if (!cache.containsKey(key)) return -1;
    	update(key);
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        if (cap == 0) return;
        if (cache.containsKey(key)){
        	cache.put(key, value);
        }
        else{
        	if (cache.size() < cap){
        		cache.put(key, value);
        	}
        	else{
        		remove();
        		cache.put(key,value);
        	}
        }
        update(key);
    }
}
