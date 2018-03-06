package com.daimens.algorithm.april;

import java.util.Arrays;

/**
 * 
 * @author DemonSong 
 * 
 * 564.Find the Closest Palindrome
 * 
 *         Given an integer n, find the closest integer (not including itself),
 *         which is a palindrome.
 * 
 *         The 'closest' is defined as absolute difference minimized between two
 *         integers.
 * 
 *         Example 1: Input: "123" Output: "121" Note: The input n is a positive
 *         integer represented by string, whose length will not exceed 18. If
 *         there is a tie, return the smaller one as answer.
 * 
 *
 */
public class SolutionDay23_564 {

	// public String nearestPalindromic(String n) {
	// long num = Long.parseLong(n);
	//
	// while (num != 0){
	// num--;
	// if (isPalindrome(String.valueOf(num))) break;
	// }
	// long first = num;
	// num = Long.parseLong(n);
	//
	// while (num != 0){
	// num++;
	// if (isPalindrome(String.valueOf(num))) break;
	// }
	//
	// long second = num;
	//
	// num = Long.parseLong(n);
	// if (second-num < num - first) return String.valueOf(second);
	//
	// return String.valueOf(first);
	// }
	//
	// private boolean isPalindrome(String num){
	// for (int i = 0, j = num.length()-1; i < j; i++,j--){
	// if (num.charAt(i) != num.charAt(j)) return false;
	// }
	// return true;
	// }

	public String nearestPalindromic(String n) {
		long z = Long.parseLong(n);
		long x = bigger(z + 1);
		long y = smaller(z - 1);
		if (x - z < z - y)
			return Long.toString(x);
		else
			return Long.toString(y);
	}

	private long bigger(long u) {
		char[] origin = Long.toString(u).toCharArray();
		int len = origin.length;
		char[] palind = Arrays.copyOf(origin, len);

		for (int i = 0; i < len / 2; i++) {
			palind[len - 1 - i] = palind[i];
		}

		// 检查生成的回文是否比原数大
		for (int i = 0; i < len; i++) {
			if (origin[i] > palind[i]) {
				for (int j = (len - 1) / 2; j >= 0; j--) {
					if (++palind[j] > '9') {
						palind[j] = '0';
					} else {
						break;
					}
				}

				for (int j = 0; j < len / 2; j++) {
					palind[len - j - 1] = palind[j];
				}
				return Long.parseLong(new String(palind));

			} else if (origin[i] < palind[i]) {
				return Long.parseLong(new String(palind));
			}
		}
		return Long.parseLong(new String(palind));
	}

	// private long bigger(long u) {
	// char[] s = Long.toString(u).toCharArray();
	// int m = s.length;
	// char[] t = Arrays.copyOf(s, m);
	// for (int i = 0; i < m / 2; i++) {
	// t[m - 1 - i] = t[i];
	// }
	// for (int i = 0; i < m; i++) {
	// if (s[i] > t[i]) {
	// for (int j = (m - 1) / 2; j >= 0; j--) {
	// if (++t[j] > '9') {
	// t[j] = '0';
	// } else {
	// break;
	// }
	// }
	// for (int j = 0; j < m / 2; j++) {
	// t[m - 1 - j] = t[j];
	// }
	// return Long.parseLong(new String(t));
	// } else if (s[i] < t[i]) {
	// return Long.parseLong(new String(t));
	// }
	// }
	// return Long.parseLong(new String(t));
	// }
	//

	private long smaller(long u) {
		char[] origin = Long.toString(u).toCharArray();
		int len = origin.length;
		char[] palind = Arrays.copyOf(origin, len);
		for (int i = 0; i < len / 2; i++) {
			palind[len - 1 - i] = palind[i];
		}
		for (int i = 0; i < len; i++) {
			if (origin[i] > palind[i]) {
				return Long.parseLong(new String(palind));
			} else if (origin[i] < palind[i]) {

				for (int j = (len - 1) / 2; j >= 0; j--) {
					if (--palind[j] < '0') {
						palind[j] = '9';
					} else {
						break;
					}
				}

				if (palind[0] == '0') {
					if (palind.length == 1) {
						return 0;
					}
					palind = new char[len - 1];
					Arrays.fill(palind, '9');
					return Long.parseLong(new String(palind));
				}

				for (int j = 0; j < len / 2; j++) {
					palind[len - j - 1] = palind[j];
				}

				return Long.parseLong(new String(palind));
			}
		}
		return Long.parseLong(new String(palind));
	}

	public static void main(String[] args) {
		SolutionDay23_564 day = new SolutionDay23_564();
		day.nearestPalindromic("1213");
	}
}
