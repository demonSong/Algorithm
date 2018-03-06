package com.daimens.algorithm.september;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SolutionDay20_L0679 {

//	char[] op = new char[] { '+', '-', '*', '/' };
//	public boolean judgePoint24(int[] nums) {
//		ops = new HashSet<>();
//		permutation(nums, 0, new boolean[4], "");
//		for (String oo : ops) {
//			Set<Double> ans = cal(oo);
//			for (double d : ans) {
//				if (Math.abs(d - 24) <= 0.0000000001) return true;
//			}
//		}
//		return false;
//	}
//
//	Set<Double> cal(String oo) {
//		int cnt = 0;
//		Set<Double> ans = new HashSet<>();
//		for (int i = 0; i < oo.length(); ++i) {
//			if (oo.charAt(i) == '+') {
//				Set<Double> left = cal(oo.substring(0, i));
//				Set<Double> right = cal(oo.substring(i + 1, oo.length()));
//				cnt++;
//				for (double a1 : left) {
//					for (double a2 : right) {
//						ans.add(a1 + a2);
//					}
//				}
//			}
//			if (oo.charAt(i) == '-') {
//				Set<Double> left = cal(oo.substring(0, i));
//				Set<Double> right = cal(oo.substring(i + 1, oo.length()));
//				cnt++;
//				for (double a1 : left) {
//					for (double a2 : right) {
//						ans.add(a1 - a2);
//					}
//				}
//			}
//			if (oo.charAt(i) == '*') {
//				Set<Double> left = cal(oo.substring(0, i));
//				Set<Double> right = cal(oo.substring(i + 1, oo.length()));
//				cnt++;
//				for (double a1 : left) {
//					for (double a2 : right) {
//						ans.add(a1 * a2);
//					}
//				}
//			}
//			if (oo.charAt(i) == '/') {
//				Set<Double> left = cal(oo.substring(0, i));
//				Set<Double> right = cal(oo.substring(i + 1, oo.length()));
//				cnt++;
//				for (double a1 : left) {
//					for (double a2 : right) {
//						ans.add(a1 / a2);
//					}
//				}
//			}
//
//		}
//		if (cnt == 0) {
//			ans.add(Double.parseDouble(oo));
//			return ans;
//		}
//		return ans;
//	}
//
//	Set<String> ops;
//	void permutation(int[] nums, int i, boolean[] vis, String ans) {
//		if (i >= nums.length) {
//			ops.add(ans.substring(0, ans.length() - 1));
//			return;
//		}
//		for (int j = 0; j < nums.length; ++j) {
//			if (!vis[j]) {
//				vis[j] = true;
//				for (int c = 0; c < 4; ++c) {
//					permutation(nums, i + 1, vis, ans + nums[j] + op[c]);
//				}
//				vis[j] = false;
//			}
//		}
//	}
	
	public boolean judgePoint24(int[] nums) {
		ops = new int[24][4];	
		permutation(nums, 0, new boolean[4], new int[4]);
		for (int i = 0; i < 24; ++i) {
			Set<Double> ans = cal(ops[i], 0, 3);
			for (double d : ans) {
				if (Math.abs(d - 24) <= 0.0000000001) return true;
			}
		}
		return false;
	}
	
	int[][] ops;
	int tot = 0;
	void permutation(int[] nums, int i, boolean[] vis, int[] tmp) {
		if (i == 4) {
			for (int j = 0; j < 4; ++j) {
				ops[tot][j] = tmp[j];
			}
			tot++;
			return;
		}
		for (int j = 0; j < nums.length; ++j) {
			if (!vis[j]) {
				tmp[i] = nums[j];
				vis[j] = true;
				permutation(nums, i + 1, vis, tmp);
				vis[j] = false;
			}
		}
	}
	
	Set<Double> cal(int[] nums, int start, int end){
		Set<Double> ans = new HashSet<>();
		if (start == end) {
			return Collections.singleton(new Double(nums[start]));
		}
		else {
			for (int i = start; i < end; ++i) {
				Set<Double> left = cal(nums, start, i);
				Set<Double> right = cal(nums, i + 1, end);
				for (double a1 : left) {
					for (double a2 : right) {
						ans.add(a1 + a2);
						ans.add(a1 - a2);
						ans.add(a1 * a2);
						ans.add(a1 / a2);
					}
				}
			}
			return ans;
		}
	}
	
	public static void main(String[] args) {
		SolutionDay20_L0679 day = new SolutionDay20_L0679();
		System.out.println(day.judgePoint24(new int[] { 3, 3, 8, 8 }));
	}
}
