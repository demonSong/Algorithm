package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 216.Combination Sum III
 * Find all possible combinations of k numbers that add up to a number n,given that only
 * numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * 
 * Example 1:
 * Input: k = 3, n = 7
 * Output:
 * [[1,2,4]]
 * 
 * Example 2:
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6],[1,3,5],[2,3,4]]
 *
 */
public class SolutionDay11_216 {
	
	//从一个无限集合中找寻符合条件的集合，分治递归的思想，两者有何区别
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<>();
		combination(res,new ArrayList<Integer>(),k,1,n);
        return res;
    }
	
	//这种递归的传参相当的有趣
	private void combination(List<List<Integer>> ans,List<Integer> comb,int k, int start,int n){
		//终止条件
		if(comb.size() == k && n == 0){
			//复制一份comb
			List<Integer> list = new ArrayList<Integer>(comb);
			ans.add(list);
			return;
		}
		for(int i = start; i <= 9; i++){
			comb.add(i);
			//这里不在使用原来的元素
			combination(ans, comb, k, i+1, n-i);
			comb.remove(comb.size()-1);
		}
	}
	
	public static void main(String[] args) {
		SolutionDay11_216 day = new SolutionDay11_216();
		day.combinationSum3(3, 9);
	}
}
