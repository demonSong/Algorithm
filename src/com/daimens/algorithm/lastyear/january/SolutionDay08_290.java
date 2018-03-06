package com.daimens.algorithm.january;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Demon Song
 * 290. Word Pattern
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between 
 * a letter in pattern and a non-empty word in str.
 * Examples:
 * 1.pattern = "abba", str = "dog cat cat dog" should return true.
 * 2.pattern = "abba", str = "dog cat cat fish" should return false.
 * 3.pattern = "abba", str = "dog cat cat fish" should return false.
 * 4.pattern = "abba", str = "dog dog dog dog" should return false.
 *
 */
public class SolutionDay08_290 {
	
	//hashMap
	public boolean wordPattern(String pattern, String str) {
		char[] patterns = pattern.toCharArray();
		String[] strs = str.split(" ");
		
		if(patterns.length != strs.length){
			return false;
		}
		Map<Character,String> map = new HashMap<Character,String>();
		Set<Character> cSet = new HashSet<Character>();
		Set<String> sSet = new HashSet<String>();
		
		for(int i = 0; i < patterns.length; i++){
			if(map.containsKey(patterns[i])){
				if(!map.get(patterns[i]).equals(strs[i])){
					return false;
				}
			}else{
				map.put(patterns[i], strs[i]);
				cSet.add(patterns[i]);
				sSet.add(strs[i]);
			}
		}
		
		if(cSet.size() != sSet.size()){
			return false;
		}
        return true;
    }
	
	public static void main(String[] args) {
		SolutionDay08_290 day08_290 = new SolutionDay08_290();
		String pattern ="abba";
		String str = "dog dog dog dog";
		day08_290.wordPattern(pattern, str);
	}
	
}
