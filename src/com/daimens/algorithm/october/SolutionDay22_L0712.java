package com.daimens.algorithm.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolutionDay22_L0712 {

	// public int minimumDeleteSum(String s1, String s2) {
	// int sum = 0;
	// for (char c : s1.toCharArray()) {
	// sum += c;
	// }
	//
	// for (char c : s2.toCharArray()) {
	// sum += c;
	// }
	//
	// String max = cal(s1.toCharArray(), s2.toCharArray());
	//
	// for (char c : max.toCharArray()) {
	// sum -= 2 * c;
	// }
	//
	// return sum;
	// }
	//
	// String cal(char[] cs1, char[] cs2) {
	// int n = cs1.length;
	// String ans = "";
	//
	// int j = -1;
	// for (int i = 0; i < n; ++i) {
	// int index = indexOf(cs1[i], cs2, j);
	// if (index != -1) {
	// ans += cs1[i];
	// j = index;
	// }
	// }
	//
	// return ans;
	// }
	//
	// int indexOf(char c, char[] cs, int s) {
	// int ans = -1;
	// int n = cs.length;
	// for (int i = s + 1; i < n; ++i) {
	// if (cs[i] == c) {
	// ans = i;
	// break;
	// }
	// }
	// return ans;
	// }

	// public int minimumDeleteSum(String s1, String s2) {
	// return f(s1.toCharArray(), 0, s2.toCharArray(), 0);
	// }
	//
	// public int f(char[] cs1, int p1, char[] cs2, int p2) {
	// if (cs1.length == p1 || cs2.length == p2) {
	// if (cs1.length == p1 && cs2.length == p2) return 0;
	// else if (cs1.length != p1) {
	// int sum = 0;
	// for (int i = p1; i < cs1.length; ++i) sum += cs1[i];
	// return sum;
	// }
	// else {
	// int sum = 0;
	// for (int i = p2; i < cs2.length; ++i) sum += cs2[i];
	// return sum;
	// }
	// }
	// int sum = 0;
	// int ans = 1 << 29;
	// int index = 0;
	// int i = p1;
	// for (; i < cs1.length; ++i) {
	// index = indexOf(cs2, cs1[i], p2);
	// if (index != -1) {
	// for (int j = p2; j < index; ++j) {
	// sum += cs2[j];
	// }
	// ans = sum;
	// ans += f(cs1, i + 1, cs2, index + 1);
	// break;
	// }
	// else {
	// sum += cs1[i];
	// }
	// }
	// ans = Math.min(ans, sum + 2 * cs1[i] + f(cs1, i + 1, cs2, index + 1));
	//
	// return ans;
	// }

	// public int minimumDeleteSum(String s1, String s2) {
	// return min(s1, s2);
	// }
	
	public int minimumDeleteSum(String s1, String s2) {
		char[] cs1 = s1.toCharArray();
		char[] cs2 = s2.toCharArray();
		int n1 = cs1.length;
		int n2 = cs2.length;
		int[][] dp = new int[n1 + 1][n2 + 1];
		
		for (int i = 0, t = 0; i < n2; ++i) {
			t += cs2[i];
			dp[0][i + 1] = t;
		}
		
		for (int i = 0, t = 0; i < n1; ++i) {
			t += cs1[i];
			dp[i + 1][0] = t;
		}
		
		for (int i = 1; i <= n1; ++i) {
			for (int j = 1; j <= n2; ++j) {
				if (cs1[i - 1] == cs2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				}
				else {
					dp[i][j] = Math.min(dp[i][j - 1] + cs2[j - 1], dp[i - 1][j] + cs1[i - 1]);
				}
			}
		}
		return dp[n1][n2];
	}
	
//	public int minimumDeleteSum(String s1, String s2) {
//		dp = new int[s1.length() + 1][s2.length() + 1];
//		return go(s1.toCharArray(), s2.toCharArray(), s1.length() - 1, s2.length() - 1);
//	}

	int[][] dp;
	int go(char[] cs1, char[] cs2, int p1, int p2) {
		if (dp[p1 + 1][p2 + 1] > 0)
			return dp[p1 + 1][p2 + 1];
		if (p1 == -1 && p2 == -1)
			return 0;
		if (p1 == -1 && p2 != -1) {
			int ans = go(cs1, cs2, p1, p2 - 1);
			ans += cs2[p2];
			dp[0][p2 + 1] = ans;
			return ans;
		}
		if (p1 != -1 && p2 == -1) {
			int ans = go(cs1, cs2, p1 - 1, p2);
			ans += cs1[p1];
			dp[p1 + 1][0] = ans;
			return ans;
		}

		if (cs1[p1] == cs2[p2]) {
			int ans = go(cs1, cs2, p1 - 1, p2 - 1);
			return Math.min(ans, go(cs1, cs2, p1 - 1, p2) + cs1[p1]);
		} else {
			int a1 = go(cs1, cs2, p1 - 1, p2);
			int a2 = go(cs1, cs2, p1, p2 - 1);
			return dp[p1 + 1][p2 + 1] = Math.min(a1 + cs1[p1], a2 + cs2[p2]);
		}
	}

	int min(String s1, String s2) {
		if (s1.contains(s2)) {
			int sum = 0;
			if (s1.length() < s2.length()) {
				for (char c : s1.toCharArray()) {
					sum -= 2 * c;
				}
			} else {
				for (char c : s2.toCharArray()) {
					sum -= 2 * c;
				}
			}

			for (char c : s1.toCharArray()) {
				sum += c;
			}

			for (char c : s2.toCharArray()) {
				sum += c;
			}

			return sum;
		} else {
			int index = indexOf(s1.charAt(0), s2);
			if (index == -1) {
				return s1.charAt(0) + min(s1.substring(1), s2);
			} else {
				int ans = 0x3f3f3f3f;
				// 不删
				int sum = 0;
				for (int i = 0; i < index; ++i) {
					sum += s2.charAt(i);
				}
				sum += min(s1.substring(1), s2.substring(index + 1));
				ans = Math.min(ans, sum);

				// 删
				ans = Math.min(ans, s1.charAt(0) + min(s1.substring(1), s2));
				return ans;
			}
		}
	}

//	public int[] intersect(int[] nums1, int[] nums2) {
//		int n1 = nums1.length;
//		int n2 = nums2.length;
//		
//		Set<Integer> res = new HashSet<Integer>();
//		Arrays.sort(nums1);
//		for (int i = 0; i < n2; ++i) {
//			int l = 0;
//			int r = n1 - 1;
//			while (l <= r) {
//				int mid = l - (l - r) / 2;
//				if (nums1[mid] < nums2[i]) {
//					l = mid + 1;
//				} else if (nums1[mid] > nums2[i]) {
//					r = mid;
//				} else {
//					res.add(nums1[l]);
//					break;
//				}
//			}
//
//		}
//		
//		Map<Integer, Integer> a1 = new HashMap<>();
//		Map<Integer, Integer> a2 = new HashMap<>();
//		for (int i = 0; i < n1; ++i) {
//			if (a1.containsKey(nums1[i])) {
//				a1.put(nums1[i], a1.get(nums1[i]) + 1);
//			}
//			else {
//				a1.put(nums1[i], 1);
//			}
//		}
//		for (int i = 0; i < n2; ++i) {
//			if (a2.containsKey(nums2[i])) {
//				a2.put(nums2[i], a2.get(nums2[i]) + 1);
//			}
//			else {
//				a2.put(nums2[i], 1);
//			}
//		}
//		
//		List<Integer> list = new ArrayList<>();
//		for (int num : res) {
//			int cnt = Math.min(a1.get(num), a2.get(num));
//			for (int i = 0; i < cnt; ++i) {
//				list.add(num);
//			}
//		}
//		
//		int[] ans = new int[list.size()];
//		for (int i = 0; i < list.size(); ++i) {
//			ans[i] = list.get(i);
//		}
//		return ans;
//	}
	
	boolean find(int[] nums1, int key) {
		int lf = 0;
		int rt = nums1.length - 1;
		while (lf <= rt) {
			int mid = lf - (lf - rt) / 2;
			if (nums1[mid] < key) {
				lf = mid + 1;
			}
			else if (nums1[mid] > key){
				rt = mid;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	public int[] intersect(int[] nums1, int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		
		Arrays.sort(nums1);
		Set<Integer> res = new HashSet<>();
		for (int i = 0; i < n2; ++i) {
			int key = nums2[i];
			if (find(nums1, key)) res.add(key);
		}
		
		
        // 统计 两个集合 每个元素出现的次数
		Map<Integer, Integer> a1 = new HashMap<>();
		Map<Integer, Integer> a2 = new HashMap<>();
		for (int i = 0; i < n1; ++i) {
			if (a1.containsKey(nums1[i])) {
				a1.put(nums1[i], a1.get(nums1[i]) + 1);
			}
			else {
				a1.put(nums1[i], 1);
			}
		}
		for (int i = 0; i < n2; ++i) {
			if (a2.containsKey(nums2[i])) {
				a2.put(nums2[i], a2.get(nums2[i]) + 1);
			}
			else {
				a2.put(nums2[i], 1);
			}
		}
		
		List<Integer> list = new ArrayList<>();
		// 从 候选集合中 取出 交集
		for (int num : res) {
            if (a1.containsKey(num) && a2.containsKey(num)){
                // 取出 两个集合中的较小次数
            	int cnt = Math.min(a1.get(num), a2.get(num));
            	// 构造交集
                for (int i = 0; i < cnt; ++i) {
                    list.add(num);
                }
            }
		}
		
		int[] ans = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			ans[i] = list.get(i);
		}
		return ans;
	}

	int indexOf(char c, String s2) {
		char[] cs = s2.toCharArray();
		int n = cs.length;
		int ans = -1;
		for (int i = 0; i < n; ++i) {
			if (cs[i] == c) {
				ans = i;
				break;
			}
		}
		return ans;
	}

	boolean contains(String s1, String s2) {
		char[] cs1 = s1.toCharArray();
		char[] cs2 = s2.toCharArray();

		int n1 = cs1.length;
		int n2 = cs2.length;

		int j = 0; // n - 1;
		for (int i = 0; i < n1; ++i) {
			while (j < n2 && cs2[j] != cs1[i])
				++j;
		}
		return j < n2;
	}

	public static void main(String[] args) {
		SolutionDay22_L0712 day = new SolutionDay22_L0712();
		System.out.println(day.contains("", ""));
		System.out.println(day.minimumDeleteSum("delete", "leet"));
	}

}
