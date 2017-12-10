package com.daimens.algorithm.december;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SolutionDay10_L0744 {
	
//    public char nextGreatestLetter(char[] letters, char target) {
//    	Set<Character> set = new HashSet<>();
//    	for (char c : letters) set.add(c);
//    	
//    	char[] let = new char[set.size()];
//    	int i = 0;
//    	for (char c : set) {
//    		let[i++] = c;
//    	}
//    	
//    	Arrays.sort(let);
//    	for (int j = 0; j < let.length; ++j) {
//    		if (let[j] > target) return let[j];
//    	}
//    	return let[0];
//    }

//    public char nextGreatestLetter(char[] letters, char target) {
//    	Arrays.sort(letters);
//    	for (int j = 0; j < letters.length; ++j) {
//    		if (letters[j] > target) return letters[j];
//    	}
//    	return letters[0];
//    }
	
	public char nextGreatestLetter(char[] letters, char target) {
    	Set<Character> set = new HashSet<>();
    	for (char c : letters) set.add(c);
    	
    	char[] let = new char[set.size()];
    	int i = 0;
    	for (char c : set) {
    		let[i++] = c;
    	}
		
    	Arrays.sort(let);
    	int idx = Arrays.binarySearch(let, target);
    	if (idx >= 0){
            if (idx + 1 >= let.length) return let[0];
            else return let[idx + 1];
        }
    	else {
    		idx = -idx - 1;
    		if (idx == let.length) return let[0];
    		else return let[idx];
    	}
	}
    
    public int upperBound(char[] letters, char target) {
    	int l = 0;
    	int r = letters.length - 1;
    	while (l < r) {
    		int m = l + (r - l) / 2;
    		if (letters[m] <= target) {
    			l = m + 1;
    		}
    		else{
    			r = m;
    		}
    	}
    	if (letters[r] > target) return r;
    	else return 0;
    }
    
    

		
	public static void main(String[] args) {
		SolutionDay10_L0744 day = new SolutionDay10_L0744();
		char[] letters = {'e', 'e','e', 'e', 'e', 'e','e','n','n'};
		System.out.println(day.nextGreatestLetter(letters, 'e'));
	}

}
