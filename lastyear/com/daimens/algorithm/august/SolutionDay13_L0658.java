package com.daimens.algorithm.august;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SolutionDay13_L0658 {
	
//	class Pair implements Comparable<Pair>{
//		int id;
//		int min;
//		public Pair(int id, int min){
//			this.id = id;
//			this.min = min;
//		}
//		@Override
//		public int compareTo(Pair that) {
//			return this.min == that.min ? this.id - that.id : this.min - that.min;
//		}
//	}
//	
//	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
//        Integer[] aux = arr.toArray(new Integer[0]);
//        int index = binarySearch(aux, x);
//        List<Integer> ans = new ArrayList<>();
//        if (arr.size() == 0) return ans;
//        Queue<Pair> queue = new PriorityQueue<>();
//        queue.offer(new Pair(index, Math.abs(aux[index] - x)));
//        for (int i = 1; i < k; ++i){
//        	if (i + index < aux.length) queue.offer(new Pair(i + index, Math.abs(aux[index + i] - x)));
//        	if (index - i >= 0) queue.offer(new Pair(index - i, Math.abs(aux[index - i] - x)));
//        }
//        for (int i = 0; i < k; ++i){
//        	ans.add(aux[queue.poll().id]);
//        }
//        Collections.sort(ans);
//        return ans;
//    }
//	
//	public int binarySearch(Integer[] arra, int x){
//		int lf = 0, rt = arra.length - 1;
//		while (lf < rt){
//			int mid = lf + (rt - lf + 1) / 2;
//			if (arra[mid] > x) rt = mid - 1;
//			else lf = mid;
//		}
//		if (arra[lf] <= x) return lf;
//		return 0;
//	}
	
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
		List<Integer> ans = new ArrayList<>();
		if (arr.size() == 0) return ans;
		Collections.sort(arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				int ax = Math.abs(a - x);
				int bx = Math.abs(b - x);
				if (ax != bx) return ax - bx;
				return a - b;
			}
		});
		
		ans = new ArrayList<Integer>(arr.subList(0, Math.min(k, arr.size())));
		Collections.sort(ans);
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay13_L0658 day = new SolutionDay13_L0658();
	}
}
