package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author DemonSong
 * 
 *         380.Insert Delete GetRandom O(1)
 * 
 *         Design a data structure that supports all following operations in
 *         average O(1) time.
 * 
 *         insert(val): Inserts an item val to the set if not already present.
 *         remove(val): Removes an item val from the set if present. getRandom:
 *         Returns a random element from current set of elements. Each element
 *         must have the same probability of being returned.
 *
 */
public class RandomizedSet {
	
	List<Integer> nums;
	Map<Integer, Integer> cache;
	Random random = new Random();
	
	/** Initialize your data structure here. */
	public RandomizedSet() {
		nums = new ArrayList<>();
		cache = new HashMap<>();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already
	 * contain the specified element.
	 */
	public boolean insert(int val) {
		if (cache.containsKey(val)) return false;
		int idx = cache.size();
		nums.add(idx,val);
		cache.put(val, idx);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the
	 * specified element.
	 */
	public boolean remove(int val) {
		if (!cache.containsKey(val)) return false;
		int idx = cache.get(val);
		if (idx != cache.size()-1){
			cache.put(nums.get(cache.size()-1), idx);
			nums.set(idx, nums.get(cache.size()-1));
		}
		cache.remove(val);
		nums.remove(nums.size()-1);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return nums.get(random.nextInt(cache.size()));
	}
	
	public static void main(String[] args) {
		RandomizedSet rs = new RandomizedSet();
		rs.insert(0);
		rs.insert(1);
		rs.remove(0);
		rs.insert(2);
		rs.remove(1);
		System.out.println(rs.getRandom());
	}
}
