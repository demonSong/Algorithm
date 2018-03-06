package com.daimens.algorithm.august;

public class SolutionDay27_L0667 {

//	public int[] constructArray(int n, int k) {
//		int[] ans = new int[n];
//		ans[0] = 1;
//		int j = 0;
//		boolean[] used = new boolean[n + 1];
//		used[1] = true;
//		used[0] = true;
//		while (k > 0){
//			j ++;
//			if (j % 2 != 0){
//				ans[j] = ans[j - 1] + k;
//				used[ans[j]] = true;
//			}
//			else{
//				ans[j] = ans[j - 1] - k;
//				used[ans[j]] = true;
//			}
//			k--;
//		}
//		
//		for (int i = 0; i < used.length; ++i){
//			if (!used[i]){
//				ans[++j] = i;
//			}
//		}
//		return ans;
//    }
	
	public int[] constructArray(int n, int k) {
		int[] ans = new int[n];
		ans[0] = 1;
		int j = 0;
		int iter = k;
		while (iter > 0){
			j ++;
			if (j % 2 != 0){
				ans[j] = ans[j - 1] + iter;
			}
			else{
				ans[j] = ans[j - 1] - iter;
			}
			iter--;
		}
		
		for (int i = k + 2; i <= n; ++i){
			ans[++j] = i;
		}
		return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay27_L0667 day = new SolutionDay27_L0667();
		System.out.println(day.constructArray(12, 5));
	}
}
