package com.daimens.algorithm.march;

public class SolutionDay18_L0500 {
	
    public String similarRGB(String color) {
    	String[] tmp = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};
    	String res = "#";
    	for (int i = 1; i < 7; i += 2) {
    		String ab = color.substring(i, i + 2);
    		int min = 0x3f3f3f3f;
    		String ans = "";
    		for (int j = 0; j < tmp.length; ++j) {
    			if (Math.abs(String2Int(tmp[j]) - String2Int(ab)) < min) {
    				min = Math.abs(String2Int(tmp[j]) - String2Int(ab));
    				ans = tmp[j];
    			}
    		}
    		res += ans.charAt(0);
    	}
    	return res;
    }
    
    public int String2Int(String ab) {
    	int a = Character.isAlphabetic(ab.charAt(0)) ? ab.charAt(0) - 'a' + 10 : ab.charAt(0) - '0';
    	int b = Character.isAlphabetic(ab.charAt(1)) ? ab.charAt(1) - 'a' + 10 : ab.charAt(1) - '0';
    	return a * 16 + b;
    }
	
	public static void main(String[] args) {
		SolutionDay18_L0500 day = new SolutionDay18_L0500();
		System.out.println(day.similarRGB("#09f166"));
	}

}
