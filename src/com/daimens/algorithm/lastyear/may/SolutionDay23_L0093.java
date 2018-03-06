package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         93.Restore IP Addresses
 * 
 *         Given a string containing only digits, restore it by returning all
 *         possible valid IP address combinations.
 * 
 *         For example: Given "25525511135",
 * 
 *         return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 *
 */
public class SolutionDay23_L0093 {
	
//	public List<String> restoreIpAddresses(String s) {
//		List<String> ans = new ArrayList<>();
//		backTrack(ans, "", 3, s, 0);
//		return ans;
//    }
//	
//	private void backTrack(List<String> ans, String ip, int k, String address, int index){
//		if (k == 0){
//			if (valid(address.substring(index))){
//				ip += address.substring(index);
//				ans.add(ip);
//				return;
//			}
//		} else {
//			for (int i = 0; i < 3; i++) {
//				if (index + i >= address.length())
//					continue;
//				String cut = address.substring(index, index + i + 1);
//				if (valid(cut)) {
//					ip += cut + ".";
//					backTrack(ans, ip, k - 1, address, index + i + 1);
//					ip = ip.substring(0, ip.length() - (i + 1) - 1);
//				}
//			}
//		}
//	}
	
	public List<String> restoreIpAddresses(String s) {
		List<String> ans = new ArrayList<>();
		for (int i = 1; i < 4; i++){
			if (i >= s.length()) continue;
			for (int j = i + 1; j < i + 4; j++){
				if (j >= s.length()) continue;
				for (int k = j + 1; k < j + 4; k++){
					if (k >= s.length()) continue;
					String s1 = s.substring(0, i);
					String s2 = s.substring(i, j);
					String s3 = s.substring(j, k);
					String s4 = s.substring(k);
					if (valid(s1) && valid(s2) && valid(s3) && valid(s4)){
						ans.add(s1+"."+s2+"."+s3+"."+s4);
					}
				}
			}
		}
		
		return ans;
	}
	
	private boolean valid(String s){
		if (s.length() >= 4 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1))
			return false;
		return Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= 255; 
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0093 day = new SolutionDay23_L0093();
		day.restoreIpAddresses("25525511135");
		String address = "25525511135";
		System.out.println(address.substring(0, 3));
		System.out.println(address.substring(3));
	}
}
