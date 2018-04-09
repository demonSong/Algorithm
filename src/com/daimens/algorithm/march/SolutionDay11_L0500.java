package com.daimens.algorithm.march;

public class SolutionDay11_L0500 {
	
    public boolean rotateString(String A, String B) {
        for (int i = 1; i < A.length(); ++i) {
        	String tmp = A.substring(i, A.length()) + A.substring(0, i);
        	if (tmp.equals(B)) return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		SolutionDay11_L0500 day = new SolutionDay11_L0500();
		System.out.println(day.rotateString("acbsada","acbsada"));
	}
	
}
