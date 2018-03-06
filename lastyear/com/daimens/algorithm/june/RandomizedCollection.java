package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         381. Insert Delete GetRandom O(1) - Duplicates allowed
 * 
 *         Design a data structure that supports all following operations in
 *         average O(1) time.
 * 
 *         Note: Duplicate elements are allowed. insert(val): Inserts an item
 *         val to the collection. remove(val): Removes an item val from the
 *         collection if present. getRandom: Returns a random element from
 *         current collection of elements. The probability of each element being
 *         returned is linearly related to the number of same value the
 *         collection contains.
 *
 */
public class RandomizedCollection {

	List<Integer> nums;
	Map<Integer, Set<Integer>> map;
	Random random = new Random();

	/** Initialize your data structure here. */
	public RandomizedCollection() {
		nums = new ArrayList<>();
		map = new HashMap<>();
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val) {
		// dup or not dup insert
		boolean dup = map.containsKey(val);
		nums.add(val);
		map.computeIfAbsent(val, a -> new LinkedHashSet<>()).add(nums.size() - 1);
		return !dup;
	}

	/**
	 * Removes a value from the collection. Returns true if the collection
	 * contained the specified element.
	 */
	public boolean remove(int val) {
		if (!map.containsKey(val))
			return false;
		int loc = map.get(val).iterator().next();
		map.get(val).remove(loc);
		if (loc < nums.size() - 1) {
			int last = nums.get(nums.size() - 1);
			nums.set(loc, last);
			map.get(last).remove(nums.size() - 1);
			map.get(last).add(loc);
		}
		nums.remove(nums.size() - 1);
		if (map.get(val).isEmpty())
			map.remove(val);
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return nums.get(random.nextInt(nums.size()));
	}
}