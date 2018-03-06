package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 119.Pascal's Triangle 2
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 *
 */
public class SolutionDay25_119 {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> list = new ArrayList<Integer>(rowIndex+1);
		list.add(1);
		
		if(rowIndex == 0){
			return list;
		}
		
		list.add(1);
		if(rowIndex == 1){
			return list;
		}
		for (int i =2;i <= rowIndex; i++){
			list.add(1);
			for(int j =i-1;j>0;j--){
				list.set(j, list.get(j) + list.get(j-1));
			}
		}
		return list;
    }
	
	//closed
	public List<Integer> generateC(int length){
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i <= length; i++) {
			tmp.add(C(i, length));
		}
		return tmp;
	}

	// 每次都要重新计算，这速度肯定是相当慢的
	public int C(int k, int n) {
		return factorial(n) / (factorial(n - k) * factorial(k));
	}

	public int factorial(int n) {
		int res = 1;
		for (int i = 1; i <= n; i++) {
			res = res * i;
		}
		return res;
	}
}
