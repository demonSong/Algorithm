package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 390.Elimination Game
 * There is a list of sorted integers from 1 to n.Starting from left to right,remove the first
 * number and every other number afterward until you reach the end of the list.
 * Repeat the previous step again,but this time from right to left,remove the right most number and 
 * every other number from the remaining numbers.
 * We keep repeating the steps again,alternating left to right and right to left,until a single number
 * remains.
 * Find the last number that remains starting with a list of length n.
 * Example:
 * Input:
 * n = 9,
 * _   _   _   _   _
 * 1 2 3 4 5 6 7 8 9
 *   _   _
 * 2 4 6 8
 * _ 
 * 2 6
 * 
 * 6
 * 
 * Output:
 * 6
 *
 */
public class SolutionDay22_390 {
//	public int lastRemaining(int n) {
//		int[] map = new int[n+1];
//		for (int i = 1; i <= n; i++){
//			map[i] = i;
//			map[0] |= i;
//		}
//		int res = 0,start = 1,end = n;
//		while (map[0] !=0){
//			res = map[0];
//			
//			//map 不为0的情况下，开始前序删除
//			for (int i = start; i <= end; i += 2) {
//				map[0] |= i;
//			}
//		}
//		return 0;
//    }
	
//	public int lastRemaining(int n) {
//		int res = 0;
//		int len = n;
//		int kkk = 2, pre = 1, end = n;
//		for (int i = 1; i < n; i++){
//			res |= i;
//		}
//		boolean flag = true;
//		while (len != 1){
//			if (flag){
//				flag = false;
//				int tmp = 0;
//				for (int i = pre; i <= end; i += kkk){
//					res |= i;
//					tmp = i;
//				}
//				pre = pre + kkk/2;
//				end = tmp != end ? end : (end - kkk/2); 
//				kkk += 2;
//				len = len /2;
//			}else{
//				flag = true;
//				int tmp = 0;
//				for (int i = end; i >= pre; i-= kkk){
//					res |= i;
//					tmp = i;
//				}
//				end = end - kkk/2;
//				kkk += 2;
//				len = len /2;
//			}
//		}
//		return res;
//	}
	
	public int lastRemaining(int n) {
		boolean left = true;
		int remaining = n;
		int step = 1;
		int head = 1;
		while (remaining > 1){
			if(left || remaining % 2 == 1){ //head 更新规则分为两种情况
				head = head + step;
			}
			
			remaining = remaining / 2;
			step = step * 2;
			left = !left;
		}
		return head;
	}
	
	public static void main(String[] args) {
		SolutionDay22_390 day = new SolutionDay22_390();
		day.lastRemaining(9);
	}
}
