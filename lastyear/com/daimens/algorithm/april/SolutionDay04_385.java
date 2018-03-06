package com.daimens.algorithm.april;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 385.Mini Parser
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Note: You may assume that the string is well-formed:
 * 1. String is non-empty.
 * 2. String does not contain white spaces.
 * 3. String contains only digits 0-9, [,-,,].
 * 
 * Example 1:
 * Given s = "324"
 * You should return a NestedInteger object which contains a single integer 324.
 * 
 * Example 2:
 * Given s = "[123,[456,[789]]]",
 * Return a NestedInteger object containing a nested list with 2 elements:
 * 
 * 1. An integer containing value 123.
 * 2. A nest list containing two elements:
 * 	i. An Integer containing value 456.
 *  ii. A nested list with one element.
 *   a. An integer containing value 789.
 *
 */
public class SolutionDay04_385 {
	
	public NestedInteger deserialize(String s) {
		if (s.length() == 0) return null;
		
		// end 
		
		if (s.charAt(0) != '['){
			NestedInteger root = new NestedInteger(Integer.parseInt(s));
			return root;
		}
		
		NestedInteger root = new NestedInteger();
		
		//"[123,[456,[789]]]"
		Queue<String> queue = new LinkedList<>();
		
		char[] sc = s.toCharArray();
		int index = 1;
		while (index < s.length()-1){
			
			int start = index, end = s.length()-2, lf = 0;
			while (index < s.length()-1){
				if(sc[index] == '['){
					lf ++;
					if (start == index)
						start = index;
				}
				else if(sc[index] == ']'){
					lf --;
					if(lf == 0){
						end = index;
						break;
					}
				}
				else if(sc[index] == ','){
					end = index-1;
					if(lf == 0)
						break;
				}
				index ++;
			}
			
			queue.add(s.substring(start, end+1));
			index ++;
		}
		
		while (!queue.isEmpty()){
			String tmp = queue.poll();
			if(tmp.length() == 0) continue;
			root.getList().add(deserialize(tmp));
		}
		
		return root;
		
    }
	
	public static void main(String[] args) {
		SolutionDay04_385 day = new SolutionDay04_385();
		
		String s = "[123,456,[788,799,833],[[]],10,[]]";
		day.deserialize(s);
	}

}


class NestedInteger{
	
	public NestedInteger() {
	}
	
	public NestedInteger(int val){
	}
	
	public boolean isInteger() {
		return false;
	}

	public Integer getInteger() {
		return null;
	}

	public void setInteger(int value) {
		
	}

	public void add(NestedInteger ni) {
	}

	public List<NestedInteger> getList() {
		return null;
	}
}

