package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 118.Pascal's Triangle
 * Given numRows,generate the first numRows of Pascal's triangle.
 * For example,given numRows =5,
 * return [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 */
public class SolutionDay19_118 {
	
	public List<List<Integer>> generate(int numRows) {
		if (numRows < 0){
			return null;
		}
		
		List<List<Integer>> res = new ArrayList<>();
		
		if (numRows >=1){
			List<Integer> data = new ArrayList<>();
			data.add(1);
			res.add(data);
		}
		
		if (numRows >=2){
			List<Integer> data = new ArrayList<>();
			data.add(1);
			data.add(1);
			res.add(data);
		}
		
		if (numRows >=3){
			for(int i =3; i<=numRows;i++){
				List<Integer> data = new ArrayList<>();
				List<Integer> prev = res.get(i-2);
				data.add(1);
				for (int j=2; j<= i-1;j++){
					data.add(prev.get(j-2) + prev.get(j-1));
				}
				data.add(1);
				res.add(data);
			}
		}
		return res;
	}
	
	//C 1 4 
//	public List<List<Integer>> generate(int numRows) {
//		List<List<Integer>> res = new ArrayList<List<Integer>>();
//		for(int i =0;i< numRows;i++){
//			res.add(generateC(i));
//		}
//        return res;
//    }
//	
//	public List<Integer> generateC(int length){
//		List<Integer> tmp = new ArrayList<Integer>();
//		for (int i = 0; i<= length; i++){
//			tmp.add(C(i,length));
//		}
//		return tmp;
//	}
//	
//	//每次都要重新计算，这速度肯定是相当慢的
//	public int C(int k,int n){
//		return factorial(n) / (factorial(n-k)*factorial(k));
//	}
//	
//	public int factorial(int n){
//		int res =1;
//		for(int i =1;i<=n;i++){
//			res =res*i;
//		}
//		return res;
//	}
	
	
	public static void main(String[] args) {
		SolutionDay19_118 day19_118 = new SolutionDay19_118();
		day19_118.generate(2);
	}
}
