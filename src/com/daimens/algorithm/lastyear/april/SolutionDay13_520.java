package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 520.Detect Capital
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * 1. All letters in this word are capitals, like "USA"
 * 2. All letters in this word are not capitals,like "leetcode"
 * 3. Only the first letter in this word is capital if it has more than one letter, like "Google".
 * 
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * Example 1:
 * Input: "USA"
 * Output: True
 * 
 * Example 2:
 * Input: "FlaG"
 * Output: False
 * 
 * Note:
 * The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 *
 */
public class SolutionDay13_520 {
	
	public boolean detectCapitalUse(String word) {

		int count = 0;
		
		for (int i = 0; i < word.length(); i++){
			if (word.toLowerCase().charAt(i)-word.charAt(i) == 32){
				count ++;
			}
		}
		
		if (count == 1) {
			return word.toLowerCase().charAt(0) - word.charAt(0) == 32;
		}
		
		return count == word.length() || count == 0;
    }

	
	public static void main(String[] args) {
		
//		char[] characters = new char[26];
// 		for (char a = 'A'; a <= 'Z'; a++){
// 			characters[a-'A'] = a;
// 		}
// 		
// 		for (char a = 'a'; a <= 'z'; a++) {
//			System.out.println(a-characters[a-'a']);
//		}
 		
 		System.out.println('z'-'Z');
	}
}
