package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 125.Valid Palindrome
 * Given a string,determine if it is a palindrome,considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note:
 * Have you consider that the string might be empty?This is a good question to ask during an interview.
 * For the purpose of this problem,we define empty string as valid palindrome.
 *
 */
public class SolutionDay24_125 {

	public boolean isPalindrome(String s){
		char[] c = s.toCharArray();
		int sz = c.length;
		if(sz < 1) return true;
		
		//定义前指针，后指针
		int i = 0;
		int j = sz-1;
		
		while(i < j){
			while(i < j && !isValid(c[i])) ++i;
			while(j > i && !isValid(c[j])) --j;
			
			//转成小写
			if(c[i] >= 'A' && c[i] <= 'Z') c[i] += 'a'-'A';
			if(c[j] >= 'A' && c[j] <= 'Z') c[j] += 'a'-'A';
			
			if(i < j && c[i] != c[j]){
				return false;
			}
			++i;
			--j;
		}
		
		
		return true;
	}
	
	private boolean isValid(char c){
		if(c >='A' && c <= 'Z') return true;
		if(c >='a' && c <= 'z') return true;
		if(c >='0' && c <= '9') return true;
		return false;
	}
}
