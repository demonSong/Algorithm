package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2718. Smallest Difference
 * 
 *         Description
 * 
 *         Given a number of distinct decimal digits, you can form one integer
 *         by choosing a non-empty subset of these digits and writing them in
 *         some order. The remaining digits can be written down in some order to
 *         form a second integer. Unless the resulting integer is 0, the integer
 *         may not start with the digit 0.
 * 
 *         For example, if you are given the digits 0, 1, 2, 4, 6 and 7, you can
 *         write the pair of integers 10 and 2467. Of course, there are many
 *         ways to form such pairs of integers: 210 and 764, 204 and 176, etc.
 *         The absolute value of the difference between the integers in the last
 *         pair is 28, and it turns out that no other pair formed by the rules
 *         above can achieve a smaller difference. Input
 * 
 *         The first line of input contains the number of cases to follow. For
 *         each case, there is one line of input containing at least two but no
 *         more than 10 decimal digits. (The decimal digits are 0, 1, ..., 9.)
 *         No digit appears more than once in one line of the input. The digits
 *         will appear in increasing order, separated by exactly one blank
 *         space. Output
 * 
 *         For each test case, write on a single line the smallest absolute
 *         difference of two integers that can be written from the given digits
 *         as described by the rules above. Sample Input
 * 
 *         1 0 1 2 4 6 7 Sample Output
 * 
 *         28
 *         
 *         
 *
 */
public class SolutionDay18_P2718 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = Integer.parseInt(in.nextLine());
		while(N-- != 0){
			String[] nums = in.nextLine().trim().split(" ");
			int[] digits = new int[nums.length];
			for(int i = 0; i <nums.length; i++){
				digits[i] = nums[i].charAt(0)-'0';
			}
			System.out.println(solve(digits));
		}
		in.close();
	}
	
	private static int solve(int[] digits){
		int n = digits.length;
		int permutate = 1 << n;
		int cut = n / 2;
		int min = Integer.MAX_VALUE;
		while (permutate-- != 0){
			String bitSet = Integer.toBinaryString(permutate);
			int[] list1 = new int[cut];
			int[] list2 = new int[n-cut];
			
			char[] bits = bitSet.toCharArray();
			String a1 = "";
			String a2 = "";
			int k = 0;
			for (int i = bits.length-1; i >= 0; i--){
				if (bits[i] == '1'){
					a1 += digits[k++];
				}else{
					a2 += digits[k++];
				}
			}
			while (k < n){
				a2 += digits[k++];
			}
			
			if (a1.length() != cut){
				continue;
			}
			
			if (a1.charAt(0) == '0' && a1.length() > 1){
				continue;
			}
			
			for (int i = 0; i < list1.length; i++){
				list1[i] = a1.charAt(i)-'0';
			}
			
			for (int i = 0; i < list2.length; i++){
				list2[i] = a2.charAt(i)-'0';
			}
			
			int[] c1 = clone(list1);
			int[] c2 = clone(list2);
			do{
				String x1 = "";
				for (int i = 0; i < list1.length; i++){
					x1 += c1[i];
				}
				c2 = clone(list2);
				do{
					String x2 = "";
					for (int i = 0; i < list2.length; i++){
						x2 += c2[i];
					}
					if (x2.charAt(0) == '0' && x2.length() > 1) continue;
					int diff = Math.abs(Integer.parseInt(x1)-Integer.parseInt(x2));
					min = Math.min(min, diff);
					
				}while(nextPermutation(c2) != null);
			}while (nextPermutation(c1) != null);
		}
		return min;
	}
	
//	private static int solve(int[] digits){
//	int n = digits.length;
//	int size1 = n / 2;
//	int size2 = size1;
//	if (n % 2 != 0){
//		size2 ++;
//	}
//	int min = Integer.MAX_VALUE;
//	do{
//		int k = 0;
//		String a1 = "";
//		for (int i = 0; i < size1; i++){
//			a1 += digits[k++];
//		}
//		String a2 = "";
//		for (int i = 0; i < size2; i++){
//			a2 += digits[k++];
//		}
//		int ans = Math.abs(Integer.parseInt(a1)-Integer.parseInt(a2));
//		min = Math.min(min, ans);
//	}while(nextPermutation(digits) != null);
//	return min;
//}
	
	private static int[] clone(int[] nums){
		int[] clone = new int[nums.length];
		for (int i = 0; i < nums.length; i++){
			clone[i] = nums[i];
		}
		return clone;
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
