package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         402.Remove K Digits
 * 
 *         Given a non-negative integer num represented as a string, remove k
 *         digits from the number so that the new number is the smallest
 *         possible.
 * 
 *         Note: The length of num is less than 10002 and will be ≥ k. The given
 *         num does not contain any leading zero. Example 1:
 * 
 *         Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the
 *         three digits 4, 3, and 2 to form the new number 1219 which is the
 *         smallest. Example 2:
 * 
 *         Input: num = "10200", k = 1 Output: "200" Explanation: Remove the
 *         leading 1 and the number is 200. Note that the output must not
 *         contain leading zeroes. Example 3:
 * 
 *         Input: num = "10", k = 2 Output: "0" Explanation: Remove all the
 *         digits from the number and it is left with nothing which is 0.
 *
 */
public class SolutionDay21_L0402 {
	
//	public String removeKdigits(String num, int k) {
//		if (k == 0) return num;
//		if (num.length() == 1) return "0";
//		String smaller = num;
//		for (int i = 0; i < num.length(); i++){
//			String tmp = num.substring(0, i) + num.substring(i+1);
//			if (compare(tmp,smaller) < 0){
//				smaller = tmp;
//			}
//		}
//		return format(removeKdigits(smaller, k-1));
//    }
//	
//	private String format(String ans){
//		if (ans.isEmpty()) return "0";
//		if (ans.charAt(0) == '0'){ 
//			return format(ans.substring(1));
//		}else{
//			return ans;
//		}
//	}
//	
//	// s1 > s2 return 1; s1 < s2 return -1
//	private int compare(String s1, String s2){
//		if (s1.length() - s2.length() > 0) return 1;
//		else if (s1.length() - s2.length() < 0) return -1;
//		//s1.length = s2.length
//		char[] c1 = s1.toCharArray();
//		char[] c2 = s2.toCharArray();
//		
//		int pos = 0;
//		while (pos < c1.length && c1[pos] == c2[pos]){
//			pos ++;
//		}
//		if (pos == c1.length) return 0;
//		return c1[pos] - c2[pos];
//	}
	
	
	//every time remove the first peek
	//stack over flow
//	public String removeKdigits(String num, int k) {
//		int len = num.length();
//		if (len <= k) return "0";
//		if (k == 0) return num;
//		if (len >= 2 && num.charAt(1) == '0') return removeKdigits(num.substring(2), k-1);
//		char[] cs = num.toCharArray();
//		int i = 1;
//		while (i < len && cs[i] >= cs[i-1]) i++;
//		num = num.substring(0, i-1)+num.substring(i);
//		return removeKdigits(num, k-1);
//	}
	
//	public String removeKdigits(String num, int k) {
//		char[] cs = num.toCharArray();
//		while(k-- != 0){
//			int i = 1;
//			while (i < cs.length && cs[i] >= cs[i-1]) i++;
//			num = num.substring(0, i-1) + num.substring(i);
//			cs = num.toCharArray();
//		}
//		return format(num);
//	}
	
	private String format(String ans) {
		char[] cs = ans.toCharArray();
		int i = 0;
		while (i < cs.length && cs[i] == '0') i++;
		ans = ans.substring(i);
		return ans.isEmpty() ? "0" : ans;
	}
	
//	public String removeKdigits(String num, int k) {
//		char[] cs = num.toCharArray();
//		Stack<Character> stack = new Stack<>();
//		for (int i = 0; i < cs.length; i++){
//			while(k > 0 && !stack.isEmpty() && stack.peek() > cs[i]){
//				stack.pop();
//				k--;
//			}
//			stack.push(cs[i]);
//		}
//		while (k > 0){
//			stack.pop();
//			k--;
//		}
//		StringBuilder sb = new StringBuilder();
//		while(!stack.isEmpty()) sb.append(stack.pop());
//		return format(sb.reverse().toString());
//	}
	
	public String removeKdigits(String num, int k) {
		char[] cs = num.toCharArray();
		char[] stack = new char[num.length()];
		int top = -1;
		for (int i = 0; i < cs.length; i++){
			while (k > 0 && top != -1 && stack[top] > cs[i]){
				top--;
				k--;
			}
			stack[++top] = cs[i];
		}
		while (k-- != 0) top--;
		return format(new String(stack,0,top));
	}
	
	public static void main(String[] args) {
		SolutionDay21_L0402 day = new SolutionDay21_L0402();
		//day.removeKdigits("1432219", 3);
		day.removeKdigits("9", 1);
	}

}
