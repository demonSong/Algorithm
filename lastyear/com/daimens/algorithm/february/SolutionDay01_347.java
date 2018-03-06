package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Demon Song
 * 347.Top K Frequent Elements
 * Given a non-empty array of integers,return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note:
 * 1. You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * 2. Your algorithm's time complexity must be better than O(nlogn),where n is the array's size. 
 *
 */
public class SolutionDay01_347 {
	
	public List<Integer> topKFrequent(int[] nums,int k){
		List<Integer>[] bucket = new List[nums.length + 1];
		
		Map<Integer,Integer> frequencyMap = new HashMap<Integer,Integer>();
		
		for(int n : nums){
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}
		
		for(int key : frequencyMap.keySet()){
			int frequency = frequencyMap.get(key);
			if(bucket[frequency] == null){
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}
		
		List<Integer> res = new ArrayList<>();
		
		for (int pos = bucket.length -1; pos >= 0 && res.size() < k; pos--){
			if(bucket[pos] != null){
				res.addAll(bucket[pos]);
			}
		}
		
		return res;
	}

}
