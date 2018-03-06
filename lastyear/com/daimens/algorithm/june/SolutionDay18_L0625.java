package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         625. Minimum Factorization My SubmissionsBack To Contest User
 *         Accepted: 541 User Tried: 732 Total Accepted: 551 Total Submissions:
 *         2508 Difficulty: Medium Given a positive integer a, find the smallest
 *         positive integer b whose multiplication of each digit equals to a.
 * 
 *         If there is no answer or the answer is not fit in 32-bit signed
 *         integer, then return 0.
 * 
 *         Example 1 Input:
 * 
 *         48 Output: 68 Example 2 Input:
 * 
 *         15 Output: 35 Discuss
 *
 * 
 * 
 */
public class SolutionDay18_L0625 {

	// public int smallestFactorization(int a) {
	// if (a < 9) return a;
	// String ans = "";
	// while (a > 9){
	// int i = 9;
	// for (; i >= 1; --i){
	// if (a % i == 0){
	// ans += i;
	// break;
	// }
	// }
	// if (i == 1){
	// return 0;
	// }
	// a = a / i;
	// }
	// ans += a;
	// char[] array = ans.toCharArray();
	// Arrays.sort(array);
	// String small = new String(array);
	// try {
	// int num = Integer.parseInt(small);
	// return num;
	// } catch (NumberFormatException e) {
	// return 0;
	// }
	// }

	// public int smallestFactorization(int a) {
	// if (a < 9) return a;
	// int[] res = new int[32];
	// int j = 0;
	// while (a > 9){
	// int i = 9;
	// for (; i >= 1; --i){
	// if (a % i == 0){
	// res[j++] = i;
	// break;
	// }
	// }
	// if (i == 1){
	// return 0;
	// }
	// a = a / i;
	// }
	// res[j] = a;
	// long ans = 0;
	// while (j >= 0){
	// ans = 10 * ans + res[j--];
	// if (ans > Integer.MAX_VALUE) return 0;
	// }
	// return (int)ans;
	// }

	public int smallestFactorization(int a) {
		if (a < 10)
			return a;
		int[] res = new int[32];

		int j = 0;
		for (int i = 9; i >= 2; --i) {
			while (a % i == 0) {
				res[j++] = i;
				a = a / i;
			}
		}
		if (a != 1)
			return 0;
		long ans = 0;
		for (int i = j - 1; i >= 0; --i) {
			ans = 10 * ans + res[i];
			if (ans > Integer.MAX_VALUE)
				return 0;
		}
		return (int) ans;
	}

	// private String smallestFactorization(int a, int x){
	// if (a < 9) return "" + a;
	// String ans = "";
	// int i = 9;
	// for (; i >= 1; --i){
	// if (a % i == 0){
	// ans = "" + i + smallestFactorization(a / i,0);
	// break;
	// }
	// }
	// if (i == 1) return "0";
	// return ans;
	// }

	public static void main(String[] args) {
		SolutionDay18_L0625 day = new SolutionDay18_L0625();
		System.out.println(day.smallestFactorization(48));
	}
}
