package com.daimens.algorithm.october;

public class SolutionDay22_L0501 {

//	public int minimumDeleteSum(String s1, String s2) {
//		int sum = 0;
//		for (char c : s1.toCharArray()) {
//			sum += c;
//		}
//
//		for (char c : s2.toCharArray()) {
//			sum += c;
//		}
//
//		String max = cal(s1.toCharArray(), s2.toCharArray());
//
//		for (char c : max.toCharArray()) {
//			sum -= 2 * c;
//		}
//
//		return sum;
//	}
//
//	String cal(char[] cs1, char[] cs2) {
//		int n = cs1.length;
//		String ans = "";
//
//		int j = -1;
//		for (int i = 0; i < n; ++i) {
//			int index = indexOf(cs1[i], cs2, j);
//			if (index != -1) {
//				ans += cs1[i];
//				j = index;
//			}
//		}
//
//		return ans;
//	}
//
//	int indexOf(char c, char[] cs, int s) {
//		int ans = -1;
//		int n = cs.length;
//		for (int i = s + 1; i < n; ++i) {
//			if (cs[i] == c) {
//				ans = i;
//				break;
//			}
//		}
//		return ans;
//	}
	
//	public int minimumDeleteSum(String s1, String s2) {
//		return f(s1.toCharArray(), 0, s2.toCharArray(), 0);
//	}
//	
//	public int f(char[] cs1, int p1, char[] cs2, int p2) {
//		if (cs1.length == p1 || cs2.length == p2) {
//			if (cs1.length == p1 && cs2.length == p2) return 0;
//			else if (cs1.length != p1) {
//				int sum = 0;
//				for (int i = p1; i < cs1.length; ++i) sum += cs1[i];
//				return sum;
//			}
//			else {
//				int sum = 0;
//				for (int i = p2; i < cs2.length; ++i) sum += cs2[i];
//				return sum;
//			}
//		}
//		int sum = 0;
//		int ans = 1 << 29;
//		int index = 0;
//		int i = p1;
//		for (; i < cs1.length; ++i) {
//			index = indexOf(cs2, cs1[i], p2);
//			if (index != -1) {
//				for (int j = p2; j < index; ++j) {
//					sum += cs2[j];
//				}
//				ans = sum;
//				ans += f(cs1, i + 1, cs2, index + 1);
//				break;
//			}
//			else {
//				sum += cs1[i];
//			}
//		}
//		ans = Math.min(ans, sum + 2 * cs1[i] + f(cs1, i + 1, cs2, index + 1));
//		
//		return ans;
//	}
	
	public int minimumDeleteSum(String s1, String s2) {
		return min(s1, s2);
	}
	
	int min(String s1, String s2) {
		if (s1.contains(s2)) {
			int sum = 0;
			if (s1.length() < s2.length()) {
				for (char c : s1.toCharArray()) {
					sum -= 2 * c;
				}
			}
			else {
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
		}
		else {
			int index = indexOf(s1.charAt(0), s2);
			if (index == -1) {
				return s1.charAt(0) + min(s1.substring(1), s2);
			}
			else{
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
		
		int j = 0;  // n - 1;
		for (int i = 0; i < n1; ++i) {
			while (j < n2 && cs2[j] != cs1[i]) ++j;
		}
		return j < n2;
	}
	
	public static void main(String[] args) {
		SolutionDay22_L0501 day = new SolutionDay22_L0501();
		System.out.println(day.contains("", ""));
		System.out.println(day.minimumDeleteSum("delete", "leet"));
	}

}
