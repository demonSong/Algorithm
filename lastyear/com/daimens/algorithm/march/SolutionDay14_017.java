package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 17.Letter Combinations of a Phone Number
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * Input: Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * Note:
 * Although the above answer is in lexicographical order,your answer could be in any order you want.
 * 
 *
 */
public class SolutionDay14_017 {
	
//	public List<String> letterCombinations(String digits) {
//		
//		if(digits.length() == 0) return new ArrayList<>();
//		
//		String[][] map ={{"a","b","c"},
//							 {"d","e","f"},
//							 {"g","h","i"},
//							 {"j","k","l"},
//							 {"m","n","o"},
//							 {"p","q","r","s"},
//							 {"t","u","v"},
//							 {"w","x","y","z"}};
//		
//		List<String> res = new ArrayList<>();
//		
//		int len = digits.length();
//		int index = 0;
//		Queue<String> queue = new LinkedList<>();
//		
//		int digit = (int)(digits.charAt(index) - '0');
//		for (String c : map[digit-2]){
//			queue.add(c);
//		}
//		
//		if(len == 1) {
//			while(!queue.isEmpty()){
//				res.add(queue.poll());
//			}
//		}
//		
//		index++;
//		
//		Queue<String> queue2 = new LinkedList<>();
//		
//		while(index < len){
//			while(!queue.isEmpty()){
//				String c = queue.poll();
//				digit = (int)(digits.charAt(index) - '0');
//				for (String cc : map[digit-2]){
//					String tmp = c +cc;
//					queue2.add(tmp);
//				}
//			}
//			index ++;
//			
//			if(index == len){
//				while(!queue2.isEmpty()){
//					res.add(queue2.poll());
//				}
//			}else{
//				//queue = queue2; // 不能直接这么写，引用一样 queue2元素增加 导致queue的元素也在增加
//				while(!queue2.isEmpty()){
//					queue.add(queue2.poll());
//				}
//			}
//		}
//		
//		return res;
//    }
//
//	public List<String> letterCombinations(String digits) {
//
//		if (digits.length() == 0)
//			return new ArrayList<>();
//
//		String[][] map = { { "a", "b", "c" }, { "d", "e", "f" }, { "g", "h", "i" }, { "j", "k", "l" },
//				{ "m", "n", "o" }, { "p", "q", "r", "s" }, { "t", "u", "v" }, { "w", "x", "y", "z" } };
//
//		List<String> res = new ArrayList<>();
//
//		int len = digits.length();
//		int index = 0;
//		Queue<String> queue = new LinkedList<>();
//
//		int digit = (int) (digits.charAt(index) - '0');
//		for (String c : map[digit - 2]) {
//			queue.add(c);
//		}
//
//		if (len == 1) {
//			while (!queue.isEmpty()) {
//				res.add(queue.poll());
//			}
//		}
//
//		index++;
//
//		while (index < len) {
//			while (!queue.isEmpty()) {
//				String c = queue.poll();
//				digit = (int) (digits.charAt(index) - '0');
//				for (String cc : map[digit - 2]) {
//					String tmp = c + cc;
//					res.add(tmp);
//				}
//			}
//			index++;
//
//			if (index != len) {
//				for (int i = 0; i < res.size(); i++) {
//					queue.add(res.get(i));
//				}
//
//				res.clear();
//			}
//		}
//
//		return res;
//	}
	
//	public List<String> letterCombinations(String digits) {
//		if (digits.length() == 0)
//			return new ArrayList<>();
//
//		LinkedList<String> ans = new LinkedList<String>();
//		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
//		ans.add("");
//		for (int i = 0; i < digits.length(); i++) {
//			
//			int x = Character.getNumericValue(digits.charAt(i));
//			while (ans.peek().length() == i) { //这句话是关键啊！
//				String t = ans.remove();
//				for (char s : mapping[x].toCharArray())
//					ans.add(t + s);
//			}
//		}
//		return ans;
//	}
	
	public List<String> letterCombinations(String digits) {

		if (digits.length() == 0)
			return new ArrayList<>();

		String[][] map = { { "a", "b", "c" }, { "d", "e", "f" }, { "g", "h", "i" }, { "j", "k", "l" },
				{ "m", "n", "o" }, { "p", "q", "r", "s" }, { "t", "u", "v" }, { "w", "x", "y", "z" } };

		LinkedList<String> res = new LinkedList<>();
		res.add("");
		
		for (int i = 0; i < digits.length(); i++){
			int digit = (int) (digits.charAt(i) - '0');
			while(res.peek().length() == i){
				String t = res.remove();
	            for(String s : map[digit-2])
	                res.add(t+s);
			}
		}

		return res;
	}
	
	
	public static void main(String[] args) {
		SolutionDay14_017 day = new SolutionDay14_017();
		day.letterCombinations("234");
		
	}

}
