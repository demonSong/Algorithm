package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3187. Backward Digit Sums
 * 
 *         Description
 * 
 *         FJ and his cows enjoy playing a mental game. They write down the
 *         numbers from 1 to N (1 <= N <= 10) in a certain order and then sum
 *         adjacent numbers to produce a new list with one fewer number. They
 *         repeat this until only a single number is left. For example, one
 *         instance of the game (when N=4) might go like this:
 * 
 *         3 1 2 4
 * 
 *         4 3 6
 * 
 *         7 9
 * 
 *         16 Behind FJ's back, the cows have started playing a more difficult
 *         game, in which they try to determine the starting sequence from only
 *         the final total and the number N. Unfortunately, the game is a bit
 *         above FJ's mental arithmetic capabilities.
 * 
 *         Write a program to help FJ play the game and keep up with the cows.
 *         Input
 * 
 *         Line 1: Two space-separated integers: N and the final sum. Output
 * 
 *         Line 1: An ordering of the integers 1..N that leads to the given sum.
 *         If there are multiple solutions, choose the one that is
 *         lexicographically least, i.e., that puts smaller numbers first.
 *         Sample Input
 * 
 *         4 16 Sample Output
 * 
 *         3 1 2 4 Hint
 * 
 *         Explanation of the sample:
 * 
 *         There are other possible sequences, such as 3 2 1 4, but 3 1 2 4 is
 *         the lexicographically smallest.
 *
 */
public class SolutionDay19_P3187 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int S = in.nextInt();
		solve(N, S);
		in.close();
	}
	
	private static void solve(int N, int S){
		int[] nums = new int[N];
		for (int i = 0; i < N; i++){
			nums[i] = i + 1;
		}
		do{
			if (S == sum(nums)) break;
		}while(nextPermutation(nums) != null);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			sb.append(nums[i] + " ");
		}
		System.out.println(sb.toString().substring(0,sb.length()-1));
	}
	
	private static int sum(int[] nums){
		if (nums.length == 1) return nums[0];
		int[] sum = new int[nums.length-1];
		for (int i = 0; i < sum.length; i++){
			sum[i] = nums[i] + nums[i+1];
		}
		return sum(sum);
	}
	
	public static int[] nextPermutation(int[] nums){
		int n = nums.length;
		int pos = n - 2;
		while (pos >= 0 && nums[pos] - nums[pos+1] >= 0){
			pos --;
		}
		if (pos == -1) return null;
		
		int j = n - 1;
		while (j > pos && nums[j] <= nums[pos]){
			j --;
		}
		swap(nums, pos, j);
		reverse(nums, pos+1, n-1);
		return nums;
	}
	
	private static void swap(int[] nums, int x, int y){
		int tmp = nums[x];
		nums[x] = nums[y];
		nums[y] = tmp;
	}
	
	private static void reverse(int[] nums, int s, int e){
		while (s < e){
			swap(nums, s, e);
			s++;
			e--;
		}
	}

}
