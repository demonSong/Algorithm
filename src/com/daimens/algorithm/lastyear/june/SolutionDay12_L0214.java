package com.daimens.algorithm.june;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.LocatorEx.Snapshot;

import jdk.nashorn.internal.ir.WhileNode;

/**
 * 
 * @author DemonSong
 * 
 *         214. Shortest Palindrome
 * 
 *         Given a string S, you are allowed to convert it to a palindrome by
 *         adding characters in front of it. Find and return the shortest
 *         palindrome you can find by performing this transformation.
 * 
 *         For example:
 * 
 *         Given "aacecaaa", return "aaacecaaa".
 * 
 *         Given "abcd", return "dcbabcd".
 *
 */
public class SolutionDay12_L0214 {
	
//	public String shortestPalindrome(String s) {
//		if (s.length() <= 1) return s;
//		int n = s.length();
//		int fir = 0;
//		int lst = n - 1;
//		StringBuilder sb = new StringBuilder();
//		char[] cs = s.toCharArray();
//		while (fir < lst){
//			if (cs[fir] == cs[lst]){
//				sb.append(cs[fir]);
//				fir++;
//				lst--;
//			}
//			else{
//				sb.append(cs[lst]);
//				lst--;
//			}
//		}
//		if (fir == lst) return sb.toString() + cs[fir] + sb.reverse().toString();
//		return sb.toString() + sb.reverse().toString();
//	}
	
	public String shortestPalindrome(String s) {
		if (s.length() <= 1) return s;
		int n = s.length();
		char[] cs = s.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = n - 1; i >= 0; --i) {
			if (isPalindrome(cs, 0, i)) return sb.toString() + s.substring(0, i+1) + sb.reverse().toString();
			else sb.append(cs[i]);
		}
		return "";
	}
	
	private boolean isPalindrome(char[] c, int i, int j){
		while (i < j){
			if (c[i] != c[j]) return false;
			i++;
			j--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolutionDay12_L0214 day = new SolutionDay12_L0214();
		System.out.println(day.isPalindrome("bbbb".toCharArray(), 0, 3));
		System.out.println(day.shortestPalindrome("abcd"));
	}
}
