package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         599. Minimum Index Sum of Two Lists
 * 
 *         Suppose Andy and Doris want to choose a restaurant for dinner, and
 *         they both have a list of favorite restaurants represented by strings.
 * 
 *         You need to help them find out their common interest with the least
 *         list index sum. If there is a choice tie between answers, output all
 *         of them with no order requirement. You could assume there always
 *         exists an answer.
 * 
 *         Example 1: Input: ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 *         ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse",
 *         "Shogun"] Output: ["Shogun"] Explanation: The only restaurant they
 *         both like is "Shogun". Example 2: Input: ["Shogun", "Tapioca
 *         Express", "Burger King", "KFC"] ["KFC", "Shogun", "Burger King"]
 *         Output: ["Shogun"] Explanation: The restaurant they both like and
 *         have the least index sum is "Shogun" with index sum 1 (0+1). Note:
 *         The length of both lists will be in the range of [1, 1000]. The
 *         length of strings in both lists will be in the range of [1, 30]. The
 *         index is starting from 0 to the list length minus 1. No duplicates in
 *         both lists.
 *
 */
public class SolutionDay30_L0599 {
	
	public String[] findRestaurant(String[] list1, String[] list2) {
		Map<String,Integer> map = new HashMap<>();
		for (int i = 0; i < list1.length; i++){
			map.put(list1[i], i);
		}
		int add = list1.length;
		int min = 1 << 30;
		for (int i = 0; i < list2.length; i++){
			if (map.containsKey(list2[i])){
				int value = map.get(list2[i]);
				map.put(list2[i], value + add + i);
				min = Math.min(min, value + add + i);
			}
		}
		List<String> ans = new ArrayList<>();
		for (String key : map.keySet()){
			int value = map.get(key);
			if (value == min){
				ans.add(key);
			}
		}
		return ans.toArray(new String[0]);
    }
	
	public static void main(String[] args) {
		
	}

}
