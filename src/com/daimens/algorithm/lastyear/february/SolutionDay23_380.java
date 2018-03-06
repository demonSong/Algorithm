package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Demon Song
 * 380.Insert Delete GetRandom O(1)
 * Design a data structure that supports all following operations in average O(1) time.
 * 1. insert(val) : Insert an item val to the set if not already present.
 * 2. remove(val) : Removes an item val from the set if present.
 * 3. getRandom() : return a  random element from current set of elements.Each Element must have the same probability
 * of being returned. 
 *
 */
public class SolutionDay23_380 {
	ArrayList<Integer> nums;
	HashMap<Integer, Integer> locs;
	Random rand = new Random();
	
	/** Initialize your data structure here. */
    public SolutionDay23_380() {
        nums = new ArrayList<>();
        locs = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = locs.containsKey(val);
        if(contain){
        	return false;
        }
        locs.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = locs.containsKey(val);
        if(!contain) return false;
        int loc = locs.get(val);
        //把要删除的元素放在列表的最后
        if(loc < nums.size() -1){
        	int lastone = nums.get(nums.size()-1);
        	nums.set(loc, lastone);
        	locs.put(lastone, loc);
        }
        locs.remove(val);
        nums.remove(nums.size()-1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
    	return nums.get( rand.nextInt(nums.size()));
    }
}
