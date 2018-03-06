package com.daimens.algorithm.april;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author DemonSong 
 * 555. Split Assembled Strings 
 * 
 * 		   Given a list of strings, you could
 *         assemble these strings together into a loop. Among all the possible
 *         loops, you need to find the lexicographically biggest string after
 *         cutting and making one breakpoint of the loop, which will make a
 *         looped string into a regular one.
 * 
 *         So, to find the lexicographically biggest string, you need to
 *         experience two phases:
 * 
 *         Assemble all the strings into a loop, where you can reverse some
 *         strings or not and connect them in the same order as given. Cut and
 *         make one breakpoint in any place of the loop, which will make a
 *         looped string into a regular string starting from the character at
 *         the cutting point. And your job is to find the lexicographically
 *         biggest one among all the regular strings.
 * 
 *         Example: Input: "abc", "xyz" Output: "zyxcba" Explanation: You can
 *         get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-",
 *         where '-' represents the looped status. The answer string came from
 *         the fourth looped one, where you could cut from the middle and get
 *         "zyxcba". 
 *         
 *         Note: The input strings will only contain lowercase
 *         letters. The total length of all the strings will not over 1000.
 *
 */
public class SolutionDay16_502 {

	// public String splitLoopedString(String[] strs) {
	// if (strs.length == 0) return "";
	// Set<String> ans = new HashSet<>();
	// backTrack(strs,0,ans);
	//
	// String max = "";
	// for (String answer : ans){
	// if (answer.compareTo(max) > 0){
	// max = answer;
	// }
	// }
	//
	// return max;
	// }

	// public String splitLoopedString(String[] strs) {
	//
	// if (strs.length == 0)
	// return "";
	//
	// Set<String> ans = new HashSet<>();
	// for (int i = 0; i < strs.length; i++) {
	// if (isOrder(strs[i])) {
	// String rev = new StringBuilder(strs[i]).reverse().toString();
	// if (rev.compareTo(strs[i]) > 0) {
	// strs[i] = rev;
	// }
	// }
	// }
	//
	// backTrack(strs, 0, ans);
	//
	// String max = "";
	// for (String answer : ans) {
	// if (answer.compareTo(max) > 0) {
	// max = answer;
	// }
	// }
	//
	// return max;
	//
	// }
	//
	// private void backTrack(String[] strs, int start,Set<String> ans){
	// if (start == strs.length){
	// return;
	// }
	//
	//
	// if (isOrder(strs[start])){
	// String tmp = "";
	// for (int i = 0; i < strs.length; i++){
	// tmp += strs[i];
	// }
	//
	// String hh = findMaxStringInOneLoop(tmp);
	// ans.add(hh);
	//
	// backTrack(strs, start+1, ans);
	//
	// }else{
	// String tmp = "";
	// for (int i = 0; i < strs.length; i++){
	// tmp += strs[i];
	// }
	//
	// String hh = findMaxStringInOneLoop(tmp);
	// ans.add(hh);
	//
	// StringBuilder sb = new StringBuilder();
	// strs[start] = sb.append(strs[start]).reverse().toString();
	//
	// tmp = "";
	// for (int i = 0; i < strs.length; i++){
	// tmp += strs[i];
	// }
	//
	// hh = findMaxStringInOneLoop(tmp);
	// ans.add(hh);
	//
	// backTrack(strs, start+1, ans);
	//
	// sb = new StringBuilder();
	// strs[start] = sb.append(strs[start]).reverse().toString();
	//
	// backTrack(strs, start+1, ans);
	// }
	//
	// }
	//
	//
	// private String findMaxStringInOneLoop(String s){
	//
	// String max = s;
	// for (int i = 1; i < s.length(); i++){
	// String tmp = s.substring(i,s.length()) + s.substring(0,i);
	// if (tmp.compareTo(max) > 0){
	// max = tmp;
	// }
	// }
	//
	// return max;
	// }
	//
	// private boolean isOrder(String s){
	// char[] cc = s.toCharArray();
	//
	// boolean inc = true;
	// for (int i = 1; i < cc.length; i++){
	// if (cc[i] < cc[i-1]){
	// inc = false;
	// }
	// }
	//
	// boolean dec = true;
	// for (int i = 1; i < cc.length; i++){
	// if (cc[i] > cc[i-1]){
	// dec = false;
	// }
	// }
	//
	// return inc || dec;
	// }

	public String splitLoopedString(String[] strs) {

		int n = strs.length;

		String max = "";

		for (int i = 0; i < n; i++) {
			StringBuilder ans = new StringBuilder();
			for (int j = i + 1; j < n; j++)
				ans.append(bigger(strs[j]));
			for (int j = 0; j < i; j++)
				ans.append(bigger(strs[j]));

			int is = strs[i].length();
			for (int j = 0; j < is; j++) {
				String c = strs[i].substring(j, is) + ans.toString() + strs[i].substring(0, j);
				if (c.compareTo(max) > 0)
					max = c;
			}

			for (int j = 0; j < is; j++) {
				String c = reverse(strs[i]).substring(j, is) + ans.toString() + reverse(strs[i]).substring(0, j);
				if (c.compareTo(max) > 0)
					max = c;
			}

		}

		return max;
	}

	private String reverse(String x) {
		return new StringBuilder(x).reverse().toString();
	}

	private String bigger(String x) {
		return reverse(x).compareTo(x) > 0 ? reverse(x) : x;
	}

	// public String splitLoopedString(String[] strs) {
	// int n = strs.length;
	// if (n == 0)
	// return "";
	// for (int i = 0; i < n; i++) {
	// String s = strs[i];
	// String rs = new StringBuilder(s).reverse().toString();
	// if (s.compareTo(rs) < 0) {
	// strs[i] = rs;
	// }
	// }
	// String best = "";
	// {
	// StringBuilder sb = new StringBuilder();
	// for (int j = 0; j < n; j++) {
	// sb.append(strs[j]);
	// }
	// String gcan = sb.toString();
	//
	// for (int j = 0; j < gcan.length(); j++) {
	// String can = gcan.substring(j) + gcan.substring(0, j);
	// if (can.compareTo(best) > 0) {
	// best = can;
	// }
	// }
	// }
	//
	// for (int i = 0; i < n; i++) {
	// StringBuilder sb = new StringBuilder(strs[i]).reverse();
	// for (int j = i + 1, k = i + 1; j < i + n; j++, k++) {
	// if (k == n)
	// k = 0;
	// sb.append(strs[k]);
	// }
	//
	// String gcan = sb.toString();
	//
	// for (int j = 0; j < strs[i].length(); j++) {
	// String can = gcan.substring(j) + gcan.substring(0, j);
	// if (can.compareTo(best) > 0) {
	// best = can;
	// }
	// }
	// }
	// return best;
	// }

	public static void main(String[] args) {
		SolutionDay16_502 day = new SolutionDay16_502();
		String[] strs = { "abc", "xyz" };
		day.splitLoopedString(strs);

	}

}
