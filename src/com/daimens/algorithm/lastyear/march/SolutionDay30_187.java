package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 187.Repeated DNA Sequences
 * All DNA is composed of a series of nucleotides abbreviated as A C G, and T, for example:"ACGAATTCCG". When studying DNA, it is 
 * sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * 
 * Return:
 * ["AAAAACCCCC","CCCCCAAAAA"]
 *
 *
 */
public class SolutionDay30_187 {
	
//	public List<String> findRepeatedDnaSequences(String s) {
//		
//		if(s.length() < 10) return new ArrayList<>();
//		
//		Map<String, Integer> map = new HashMap<>();
//		for (int i = 0; i + 9 < s.length(); i++) {
//			String tmp = s.substring(i, i + 10);
//			map.put(tmp, map.getOrDefault(tmp, 0) +1);
//		}
//		
//		List<String> ans = new ArrayList<>();
//		for (String key : map.keySet()){
//			if(map.get(key) >= 2){
//				ans.add(key);
//			}
//		}
//		
//        return ans;
//    }
	
	public List<String> findRepeatedDnaSequences(String s) {
		Set<Integer> words = new HashSet<>();
		Set<Integer> doublewords = new HashSet<>();
		
		List<String> rv = new ArrayList<>();
		char[] map = new char[26];
		
		map['C'-'A'] = 1;
		map['G'-'A'] = 2;
		map['T'-'A'] = 3;
		
		for (int i = 0; i + 9 < s.length(); i++){
			int v = 0;
			for (int j = i; j < i + 10; j ++){
				v <<= 2;
				v |= map[s.charAt(j) - 'A'];
			}
			
			if(!words.add(v) && doublewords.add(v)){ //add表示当用重复元素时，set没有变换且返回 false 首次插入为true，重复插入为false
				rv.add(s.substring(i, i+10));
			}
		}
		return rv;
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay30_187 day = new SolutionDay30_187();
		
		day.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
		Set<Integer> teSet = new HashSet<>();
		System.out.println(teSet.add(2)); //首次插入为true
		System.out.println(teSet.add(2)); //重复插入为false
	}

}
