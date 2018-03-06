package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         468.Validate IP Address
 * 
 *         Write a function to check whether an input string is a valid IPv4
 *         address or IPv6 address or neither.
 * 
 *         IPv4 addresses are canonically represented in dot-decimal notation,
 *         which consists of four decimal numbers, each ranging from 0 to 255,
 *         separated by dots ("."), e.g.,172.16.254.1;
 * 
 *         Besides, leading zeros in the IPv4 is invalid. For example, the
 *         address 172.16.254.01 is invalid.
 * 
 *         IPv6 addresses are represented as eight groups of four hexadecimal
 *         digits, each group representing 16 bits. The groups are separated by
 *         colons (":"). For example, the address
 *         2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we
 *         could omit some leading zeros among four hexadecimal digits and some
 *         low-case characters in the address to upper-case ones, so
 *         2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit
 *         leading zeros and using upper cases).
 * 
 *         However, we don't replace a consecutive group of zero value with a
 *         single empty group using two consecutive colons (::) to pursue
 *         simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid
 *         IPv6 address.
 * 
 *         Besides, extra leading zeros in the IPv6 is also invalid. For
 *         example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is
 *         invalid.
 * 
 *         Note: You may assume there is no extra space or special characters in
 *         the input string.
 * 
 *         Example 1: Input: "172.16.254.1"
 * 
 *         Output: "IPv4"
 * 
 *         Explanation: This is a valid IPv4 address, return "IPv4". Example 2:
 *         Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 
 *         Output: "IPv6"
 * 
 *         Explanation: This is a valid IPv6 address, return "IPv6". Example 3:
 *         Input: "256.256.256.256"
 * 
 *         Output: "Neither"
 * 
 *         Explanation: This is neither a IPv4 address nor a IPv6 address.
 *         Subscribe to see which companies asked this question.
 *
 */
public class SolutionDay12_L0468 {
	
	String IPv4 = "IPv4";
	String IPv6 = "IPv6";
	public String validIPAddress(String IP) {
		if (IP.isEmpty()) return "Neither";
		String ans = IP.contains(".") ? IPv4 : IPv6;
		if (ans.equals(IPv4)){
			ans = validIPv4(IP) ? ans : "Neither";
		}
		else{
			ans = validIPv6(IP) ? ans : "Neither";
		}
		return ans;
    }
	
	private boolean validIPv4(String IP){
		if (IP.charAt(IP.length()-1) == '.' || IP.charAt(0) == '.') return false;
		String[] nums = IP.split("\\.");
		if (nums.length != 4) return false;
		for (String num : nums){
			char[] c = num.toCharArray();
			if (num.isEmpty() || (c[0] == '0' && c.length != 1)) return false;
			int n = 0;
			int i = 0;
			while (i < c.length && c[i] >= '0' && c[i] <= '9'){
				n = 10 * n + c[i++] - '0';
			}
			if (!(n >= 0 && n <= 255) || i < c.length) return false;
		}
		return true;
	}

	private boolean validIPv6(String IP){
		if (IP.charAt(IP.length()-1) == ':' || IP.charAt(0) == ':') return false;
		String[] nums = IP.split(":");
		if (nums.length != 8) return false;
		for (String num : nums){
			if (num.isEmpty()) return false;
			char[] c = num.toCharArray();
			if (c.length >= 5) return false;
			for (int i = 0; i < c.length; ++i){
				if (! (c[i] >= '0' && c[i] <= '9' || c[i] >= 'a' && c[i] <= 'f' || c[i] >= 'A' && c[i] <= 'F'))
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolutionDay12_L0468 day = new SolutionDay12_L0468();
		System.out.println(day.validIPAddress("1e1.4.5.6"));
	}

}
