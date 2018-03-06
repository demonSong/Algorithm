package com.daimens.algorithm.august;

public class SolutionDay13_L0657 {
	
	public boolean judgeCircle(String moves) {
		int[] map = new int[26];
		for (char c : moves.toCharArray()){
			map[c - 'A'] ++;
		}
		if (map['L' - 'A'] == map['R' - 'A'] && map['U' - 'A'] == map['D' - 'A']) return true;
		return false;
    }
	
	public static void main(String[] args) {
		SolutionDay13_L0657 day = new SolutionDay13_L0657();
		System.out.println(day.judgeCircle("LDRRLRUULR"));
	}
}
