package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 500.Keyboard Row
 * Given a list of words, return the words that can be typed using letters of alphabet on only row's of American keyboard
 * like the image below.
 * 
 * Example 1:
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 * Note:
 * 1. You may use one character in the keyboard more than once.
 * 2. You may assume the input string will only contain letters of alphabet.
 *
 */
public class SolutionDay10_500 {
	
	 public String[] findWords(String[] words) {
		 
		 char[][] keybords = {{'q','w','e','r','t','y','u','i','o','p'},{'a','s','d','f','g','h','j','k','l'},{'z','x','c','v','b','n','m'}};
		 
		 Map<Character, Integer> map = new HashMap<>();
		 int count = 1;
		 for (char[] row : keybords){
			 for (char word : row){
				 map.put(word, count);
			 }
			 count ++;
		 }
		 
		 List<String> ans = new ArrayList<>();
		 for (String word : words){
			 
			 int row = map.get(word.toLowerCase().charAt(0));
			 boolean isInRow = true;
			 for (int i = 1; i < word.length(); i++){
				 if (row != map.get(word.toLowerCase().charAt(i))){
					 isInRow = false;
					 break;
				 }
			 }
			 
			 if (isInRow){
				 ans.add(word);
			 }
		 }
		 
		 return ans.toArray(new String[0]);
	 }
	 
	 public static void main(String[] args) {
		 SolutionDay10_500 day = new SolutionDay10_500();
		 String[] words = {"Hello", "Alaska", "Dad", "Peace"};
		 day.findWords(words);
	}

}
