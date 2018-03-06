package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

public class SolutionDay12_503 {
	
	public List<String> wordsAbbreviation(List<String> dict) {
		List<String> res = new ArrayList<>();
		//第一个元素
		
		int index = 0;
		while(dict.get(index).length() <= 3 && index < dict.size()){
			res.add(dict.get(index));
			index ++;
		}
		
		res.add(abbreviated(dict.get(index))); // 第一个元素 特殊处理
		
		for (int i = index+1; i < dict.size(); i++){
			
			while(dict.get(index).length() <= 3 && index < dict.size()){
				res.add(dict.get(index));
				index ++;
			}
			
		}
		
		return null;
    }
	
	private String abbreviated(String s){
		int first = 0;
		int last = s.length()-1;
		
		StringBuilder sb = new StringBuilder();
		sb.append(s.charAt(first));
		sb.append(s.length()-2);
		sb.append(s.charAt(last));
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SolutionDay12_503 day = new SolutionDay12_503();
	}

}
