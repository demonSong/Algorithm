package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 557.Reverse Words in a String III
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving
 * whitespace and initial word order.
 * Example 1:
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Note:
 * In the string, each word is separated by single space and there will not be any extra space in the string.
 *
 */
public class SolutionDay09_500 {
	
	public String reverseWords(String s) {
	
		if (s.length() == 0) return s;
		
		String[] words = s.split(" ");
		
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < words.length; i++){
			StringBuilder tmp = new StringBuilder(words[i]);
			ans.append(tmp.reverse().toString()+" ");
		}
		
        return ans.toString().substring(0,ans.toString().length()-1);
    }
	
	public static void main(String[] args) {
		SolutionDay09_500 day = new SolutionDay09_500();
	}

}
