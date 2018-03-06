package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Demon Song
 * 77.Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of 1...n.
 * For example,
 * if n = 4 and k = 2, a solution is:
 * [
 *  [2,4],
 *  [3,4],
 *  [2,3],
 *  [1,2],
 *  [1,3],
 *  [1,4]
 * ]
 *
 */
public class SolutionDay24_077 {
	//典型的backTrack
	public List<List<Integer>> combine(int n, int k) {
		Set<List<Integer>> res = new HashSet<>();
		helper(res, new ArrayList<>(),1,n, k);
        return new ArrayList<>(res);
    }
	
	//元素可重复
	private void helper(Set<List<Integer>> res, List<Integer> tmp,int start,int end,int k){
		if(tmp.size() == k){
			res.add(new ArrayList<>(tmp));
			return;
		}
		
		for (int i = start; i <= end; i++){

			tmp.add(i);
			helper(res, tmp, i + 1, end, k);
			tmp.remove(tmp.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		SolutionDay24_077 day = new SolutionDay24_077();
		day.combine(4, 2);
	}
}
