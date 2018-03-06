package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         432.All O'one Data Structure
 * 
 *         Implement a data structure supporting the following operations:
 * 
 *         Inc(Key) - Inserts a new key with value 1. Or increments an existing
 *         key by 1. Key is guaranteed to be a non-empty string. Dec(Key) - If
 *         Key's value is 1, remove it from the data structure. Otherwise
 *         decrements an existing key by 1. If the key does not exist, this
 *         function does nothing. Key is guaranteed to be a non-empty string.
 *         GetMaxKey() - Returns one of the keys with maximal value. If no
 *         element exists, return an empty string "". GetMinKey() - Returns one
 *         of the keys with minimal value. If no element exists, return an empty
 *         string "". Challenge: Perform all these in O(1) time complexity.
 *
 */
//public class AllOne {
//
//	Map<String, Integer> map;
//	String max;
//	String min;
//	int minVal;
//	int maxVal;
//	
//	static final int INF = 1 << 30;
//	
//	/** Initialize your data structure here. */
//	public AllOne() {
//		map = new HashMap<>();
//		max = "";
//		min = "";
//		minVal = INF;
//		maxVal = -INF;
//	}
//
//	/**
//	 * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
//	 */
//	public void inc(String key) {
//		map.put(key, map.getOrDefault(key, 0) + 1);
//		if (map.get(key) < minVal){
//			min = key;
//			minVal = map.get(key);
//		}
//		if (map.get(key) > maxVal){ 
//			max = key;
//			maxVal = map.get(key);
//		}
//	}
//
//	/**
//	 * Decrements an existing key by 1. If Key's value is 1, remove it from the
//	 * data structure.
//	 */
//	public void dec(String key) {
//		if (!map.containsKey(key)) return;
//		int val = map.get(key);
//		if (val == 1){
//			map.remove(key);
//			if (min.equals(key)) min = "";
//			if (max.equals(key)) max = "";
//		}
//		else{
//			map.put(key, --val);
//			if (map.get(key) < minVal){
//				min = key;
//				minVal = map.get(key);
//			}
//			if (map.get(key) > maxVal){ 
//				max = key;
//				maxVal = map.get(key);
//			}
//		}
//	}
//
//	/** Returns one of the keys with maximal value. */
//	public String getMaxKey() {
//		return max;
//	}
//
//	/** Returns one of the keys with Minimal value. */
//	public String getMinKey() {
//		return min;
//	}
//	
//	public static void main(String[] args) {
//		AllOne one = new AllOne();
//		one.inc("hello");
//		one.inc("hello");
//		System.out.println(one.getMaxKey());
//		System.out.println(one.getMinKey());
//		one.inc("leetcode");
//		System.out.println(one.getMaxKey());
//		System.out.println(one.getMinKey());
//	}
//}

public class AllOne {
	
	private Bucket head;
	private Bucket tail;
	
	private Map<String, Integer> keyCountMap;
	private Map<Integer, Bucket> countBucketMap;
	
	class Bucket{
		int count;
		Set<String> keySet;
		Bucket prev;
		Bucket next;
		public Bucket(int cnt){
			count = cnt;
			keySet = new HashSet<>();
		}
	}

    /** Initialize your data structure here. */
    public AllOne() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        keyCountMap = new HashMap<>();
        countBucketMap = new HashMap<>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (keyCountMap.containsKey(key)){
        	changeKey(key, 1);
        }
        else{
        	keyCountMap.put(key, 1);
        	if (head.next.count != 1)
        		addBucketAfter(new Bucket(1), head);
        	head.next.keySet.add(key);
        	countBucketMap.put(1, head.next);
        }
    }
    
    private void changeKey(String key, int step){
    	int val = keyCountMap.get(key);
    	keyCountMap.put(key, val + step);
    	Bucket cur = countBucketMap.get(val);
    	Bucket newNode;
    	if (countBucketMap.containsKey(val + step)){
    		newNode = countBucketMap.get(val + step);
    	}else{
    		newNode = new Bucket(val + step);
    		countBucketMap.put(val + step, newNode);
    		addBucketAfter(newNode, step == 1 ? cur : cur.prev);
    	}
    	newNode.keySet.add(key);
    	removeKeyFromBucket(cur,key);
    }
    
    private void removeKeyFromBucket(Bucket cur, String key){
    	cur.keySet.remove(key);
    	if (cur.keySet.isEmpty()){
    		countBucketMap.remove(cur.count);
    		removeBucketFromList(cur);
    	}
    }
    
    private void removeBucketFromList(Bucket bucket){
    	bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
        bucket.next = null;
        bucket.prev = null;
    }
    
    private void addBucketAfter(Bucket node, Bucket head){
    	head.next.prev = node;
    	node.prev = head;
    	node.next = head.next;
    	head.next = node;
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (keyCountMap.containsKey(key)){
        	int val = keyCountMap.get(key);
        	if (val == 1){
        		keyCountMap.remove(key);
        		removeKeyFromBucket(countBucketMap.get(val), key);
        	}
        	else{
        		changeKey(key, -1);
        	}
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.prev == head ? "" : (String) tail.prev.keySet.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : (String) head.next.keySet.iterator().next();        
    }
}
