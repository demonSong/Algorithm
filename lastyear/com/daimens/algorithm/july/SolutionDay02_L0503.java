package com.daimens.algorithm.july;

import java.util.List;
import java.util.PriorityQueue;

public class SolutionDay02_L0503 {
	
//	public int[] smallestRange(List<List<Integer>> nums) {
//		int lo = Integer.MIN_VALUE, hi = Integer.MAX_VALUE;
//		for (int i = 0; i < nums.size(); ++i){
//			lo = Math.max(lo, nums.get(i).get(0));
//			hi = Math.min(hi, nums.get(i).get(nums.get(i).size() - 1));
//		}
//		
//		int loo = lo, hoo = hi;
//		while (loo < hoo){
//			int mid = loo + (hoo - loo + 1) / 2;
//			if (!valid(mid, nums, false)) hoo = mid - 1;
//			else loo = mid;
//		}
//		
//		while (lo < hi){
//			int mid = lo + (hi - lo) / 2;
//			if (!valid(mid, nums, true)) lo = mid + 1;
//			else hi = mid;
//		}
//		return new int[]{loo,hi};
//    }
//	
//	public boolean valid(int mid, List<List<Integer>> nums, boolean isUp){
//		if (isUp){
//			for (List<Integer> num : nums){
//				int idx = upBound(num, mid);
//				if (idx == -1) return false;
//			}
//			return true;
//		}
//		else{
//			for (List<Integer> num : nums){
//				int idx = loBound(num, mid);
//				if (idx == -1) return false;
//			}
//			return true;
//		}
//	}
//	
//	public int upBound(List<Integer> nums, int key){
//		int lf = 0, rt = nums.size() - 1;
//		while (lf < rt){
//			int mid = lf + (rt - lf + 1) / 2;
//			if (nums.get(mid) < key) rt = mid - 1;
//			else lf = mid;
//		}
//		if (nums.get(lf) >= key) return lf;
//		return -1;
//	}
//	
//	public int loBound(List<Integer> nums, int key){
//		int lf = 0, rt = nums.size() - 1;
//		while (lf < rt){
//			int mid = lf + (rt - lf) / 2;
//			if (nums.get(mid) > key) lf = mid + 1;
//			else rt = mid;
//		}
//		if (nums.get(rt) <= key) return rt;
//		return -1;
//	}
	
	class Node{
		int idx;
		int row;
		int val;
		
		public Node(int idx, int row, int val){
			this.idx = idx;
			this.row = row;
			this.val = val;
		}
	}
	
	public int[] smallestRange(List<List<Integer>> nums) {
		int max = Integer.MIN_VALUE;
		
		PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> (a.val - b.val));
		for (int i = 0; i < nums.size(); ++i){
			Node node = new Node(i, 0, nums.get(i).get(0));
			queue.offer(node);
			max = Math.max(max, node.val);
		}
		
		int range = Integer.MAX_VALUE;
		int start = -1, end = -1;
		
		while (queue.size() == nums.size()){
			Node curr = queue.poll();
			
			if (max - curr.val < range){
				range = max - curr.val;
				start = curr.val;
				end = max;
			}
			if (curr.row + 1 < nums.get(curr.idx).size()){
				curr.val = nums.get(curr.idx).get(curr.row + 1);
				curr.row = curr.row + 1;
				queue.offer(curr);
				max = Math.max(max, curr.val);
			}
		}
		return new int[]{start, end};
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay02_L0503 day = new SolutionDay02_L0503();
		
	}
}
