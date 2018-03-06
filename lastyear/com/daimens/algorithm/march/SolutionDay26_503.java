package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SolutionDay26_503 {
	
//public int removeBoxes(int[] boxes) {
//		
//		if(boxes.length == 0) return 0;
//		
//		List<Integer> ans = new ArrayList<>();
//		for (int num : boxes){
//			ans.add(num);
//		}
//		
//		find(ans);
//		
//        return sum;
//    }
//	
//	int sum = 0;
//	private void find(List<Integer> arrays){
//		if(arrays.size() == 0) return;
//		
//		Map<Integer,Integer> map = new HashMap<>();
//		
//		for (int i = 0; i < arrays.size(); i++) {
//			map.put(arrays.get(i), map.getOrDefault(arrays.get(i), 0)+1);
//		}
//		
//		int max = Integer.MIN_VALUE;
//		for(int count : map.values()){
//			if(count > max){
//				max = count;
//			}
//		}
//		sum += max * max;
//		
//		int value = 0;
//		for(int key : map.keySet()){
//			if(map.get(key) == max){
//				value = key;
//			}
//		}
//		
//		int index = 0;
//		while(index < arrays.size()){
//			List<Integer> ans = new ArrayList<>();
//			//head
//			while(index < arrays.size() && arrays.get(index) == value) index++;
//			
//			while(index < arrays.size() && arrays.get(index) != value){
//				ans.add(arrays.get(index));
//				index ++;
//			}
//			
//			find(ans);
//			
//			//tail
//			while(index < arrays.size() && arrays.get(index) == value){
//				index++;
//			}
//		}
//		
//	}
	
	
//	public int removeBoxes(int[] boxes) {
//
//		if (boxes.length == 0)
//			return 0;
//
//		List<Integer> ans = new ArrayList<>();
//		for (int num : boxes) {
//			ans.add(num);
//		}
//
//		find(ans);
//
//		return sum;
//	}
//
//	int sum = 0;
//
//	private void find(List<Integer> arrays) {
//		if (arrays.size() == 0)
//			return;
//
//		Map<Integer, Integer> map = new HashMap<>();
//
//		for (int i = 0; i < arrays.size(); i++) {
//			map.put(arrays.get(i), map.getOrDefault(arrays.get(i), 0) + 1);
//		}
//
//		int max = Integer.MIN_VALUE;
//		for (int count : map.values()) {
//			if (count > max) {
//				max = count;
//			}
//		}
//		sum += max * max;
//		
//		int value = 0;
//		for (int key : map.keySet()) {
//			if (map.get(key) == max) {
//				value = key;
//			}
//		}
//
//		int i = 0, j = arrays.size() - 1;
//
//		List<Integer> res = new ArrayList<>();
//		while (i < arrays.size() && arrays.get(i) != value) {
//			res.add(arrays.get(i));
//			i++;
//		}
//
//		LinkedList<Integer> tmp = new LinkedList<>();
//		while (j >= 0 && arrays.get(j) != value) {
//			tmp.addFirst(arrays.get(j));
//			j--;
//		}
//
//		for (int num : tmp) {
//			res.add(num);
//		}
//
//		find(res);
//
//		while (i < j) {
//			List<Integer> ans = new ArrayList<>();
//			// head
//			while (i < j && arrays.get(i) == value)
//				i++;
//
//			while (i < j && arrays.get(i) != value) {
//				ans.add(arrays.get(i));
//				i++;
//			}
//
//			find(ans);
//
//			// tail
//			while (i < arrays.size() && arrays.get(i) == value) {
//				i++;
//			}
//		}
//
//	}
	
//	public int removeBoxes(int[] boxes) {
//		
//		return 0;
//	}
	
	public int removeBoxes(int[] boxes) {
		if (boxes == null || boxes.length == 0) {
			return 0;
		}

		int size = boxes.length;
		int[][][] dp = new int[size][size][size];

		return get(dp, boxes, 0, size - 1, 1);
	}

	private int get(int[][][] dp, int[] boxes, int i, int j, int k) {
		if (i > j) {
			return 0;
		} else if (i == j) {
			return k * k;
		} else if (dp[i][j][k] != 0) {
			return dp[i][j][k];
		} else {
			int temp = get(dp, boxes, i + 1, j, 1) + k * k;

			for (int m = i + 1; m <= j; m++) {
				if (boxes[i] == boxes[m]) {
					temp = Math.max(temp, get(dp, boxes, i + 1, m - 1, 1) + get(dp, boxes, m, j, k + 1));
				}
			}

			dp[i][j][k] = temp;
			return temp;
		}

	}
	
	
	public static void main(String[] args) {
		SolutionDay26_503 day = new SolutionDay26_503();
		
		int[] boexes = {1, 1, 1, 2, 4, 8, 1, 9, 1, 2, 9, 7, 6, 3, 2, 7, 6, 5, 4, 6, 4, 4, 2, 3, 3, 1, 7, 8, 6, 9, 1, 1, 8, 10, 1, 4, 6, 7, 7, 1, 6, 10, 7, 7, 8, 6, 1, 5, 4, 3};
		day.removeBoxes(boexes);
	}

}
