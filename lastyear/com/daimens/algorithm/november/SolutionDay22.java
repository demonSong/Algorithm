package com.daimens.algorithm.november;

/**
 * 
 * @author Demon Song
 * 409. Longest Palindrome
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * 
 * Example:
 * 
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 * 
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 */
public class SolutionDay22 {
	public int longestPalindrome(String s) {
		int[] bucket = new int[58];
		for (char temp : s.toCharArray()){
			bucket[temp-'A']++;
		}
		
		int palindrome = 0;
		boolean pureEven = true;
		for (int i = 0 ; i < bucket.length; i++){
			if (bucket[i] %2 == 0){
				palindrome += bucket[i];
			}
			else{
				pureEven = false;
				if(bucket[i] >= 3){
					palindrome += (bucket[i]-1);
				}
			}
		}
        return pureEven ? palindrome:palindrome+1;
    }
	
	public static void main(String[] args) {
		// Z - A  0 - 25
		// z - A  32 - 57
		System.out.println('z'-'A');
	}
}
